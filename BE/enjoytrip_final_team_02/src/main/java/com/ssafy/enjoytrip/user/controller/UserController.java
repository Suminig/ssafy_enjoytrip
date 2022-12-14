package com.ssafy.enjoytrip.user.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.enjoytrip.spot.Spot;
import com.ssafy.enjoytrip.user.User;
import com.ssafy.enjoytrip.user.model.mapper.UserMapper;
import com.ssafy.enjoytrip.user.model.service.JwtServiceImpl;
import com.ssafy.enjoytrip.user.model.service.RegisterMail;
import com.ssafy.enjoytrip.user.model.service.UserService;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private RegisterMail registerMail;
	
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	// ????????? ?????? ??????
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/login");
		return modelAndView;
	}

	@GetMapping("/profile")
	public ModelAndView profile() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/profile");
		return modelAndView;
	}

	@GetMapping("/modify")
	public ModelAndView mvModify() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/modify");
		return modelAndView;
	}

	// ?????????
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "????????? ??? ????????? ????????????(?????????, ????????????).", required = true) User user) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			User loginUser = userService.login(user);
			if (loginUser != null) {
				String accessToken = jwtService.createAccessToken("id", loginUser.getId());// key, data
				String refreshToken = jwtService.createRefreshToken("id", loginUser.getId());// key, data
				userService.saveRefreshToken(user.getId(), refreshToken);
				logger.debug("????????? accessToken ?????? : {}", accessToken);
				logger.debug("????????? refreshToken ?????? : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("????????? ?????? : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// ????????????
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "????????? ????????? ?????????.", required = true) String userid,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("?????? ????????? ??????!!!");
			try {
//				????????? ????????? ??????.
				User user = userService.userInfo(userid);
				resultMap.put("userInfo", user);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("???????????? ?????? : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("?????? ????????? ??????!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/logout/{userid}")
	public ResponseEntity<?> removeToken(@PathVariable("userid") String userid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			userService.deleteRefreshToken(userid);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("???????????? ?????? : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	// Access Token ?????????
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody User user, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		logger.debug("token : {}, memberDto : {}", token, user);
		if (jwtService.checkToken(token)) {
			if (token.equals(userService.getRefreshToken(user.getId()))) {
				String accessToken = jwtService.createAccessToken("userid", user.getId());
				logger.debug("token : {}", accessToken);
				logger.debug("??????????????? ??????????????? ?????????!!!");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		} else {
			logger.debug("????????????????????? ?????????!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// ?????? ?????? ?????? ??????
	@GetMapping("/join")
	public ModelAndView join() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/join");
		return modelAndView;
	}

	// ?????? ??????
	@PostMapping("/join")
	public ResponseEntity<?> join(@RequestBody Map<String, String> map) {
		try {
			userService.addUser(map);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// ????????? ?????? ??????
	@GetMapping("/{id}")
	public ResponseEntity<?> idCheck(@PathVariable String id) {
		try {
			int check = userService.idcheck(id);
			return new ResponseEntity<Integer>(check, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// ?????? ?????? ??????
	@PutMapping("/modify")
	public ResponseEntity<?> modify(@Value("${file.path.upload-files}") String filePath, User user,
			@RequestParam("upfile") MultipartFile file) {
		try {
			if (!file.isEmpty()) {
				String profile = "profile";
				String saveFolder = filePath + profile;
				logger.debug("?????? ?????? : {}", saveFolder);
				File folder = new File(saveFolder);
				if (!folder.exists())
					folder.mkdirs();
				String originalFileName = file.getOriginalFilename();
				if (!originalFileName.isEmpty()) {
					String saveFileName = UUID.randomUUID().toString()
							+ originalFileName.substring(originalFileName.lastIndexOf('.'));
					logger.debug("?????? ?????? ?????? : {}, ?????? ?????? ?????? ?????? : {}", file.getOriginalFilename(), saveFileName);
					file.transferTo(new File(folder, saveFileName));
					user.setImage(profile + "/" + saveFileName);
				}
			}

			userService.updateUser(user);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// ?????? ??????
	@DeleteMapping("/delete/{uid}")
	public ResponseEntity<?> delete(@PathVariable int uid) {
		try {
			userService.removeUser(uid);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/findId")
	public ResponseEntity<?> mailFindId(@RequestBody Map<String, String> map) {
		try {
			String email = map.get("email");
			String id = map.get("id");
			registerMail.sendFindIdMessage(email, id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/findId/{email}")
	public ResponseEntity<?> getIdByEmail(@PathVariable("email") String email) {
		try {
			String id = userService.getIdByEmail(email);
			return new ResponseEntity<String>(id, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/findPw/{id}")
	public ResponseEntity<?> getUidForPw(@PathVariable("id") String id) {
		try {
			System.out.println(id);
			User user = userService.getUidForPw(id);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/findPw/{email}")
	public ResponseEntity<?> mailFindPw(@PathVariable("email") String email) {
		try {
			String tempPw = registerMail.sendFindPwMessage(email);
			return new ResponseEntity<String>(tempPw, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PutMapping("/findPw")
	public ResponseEntity<?> changeTempPwd(@RequestBody Map<String, String> map) {
		try {
			userService.changeTempPw(map);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
