package com.ssafy.enjoytrip.hotplace.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.enjoytrip.board.Board;
import com.ssafy.enjoytrip.board.controller.BoardController;
import com.ssafy.enjoytrip.board.model.service.BoardService;
import com.ssafy.enjoytrip.hotplace.model.Hotplace;
import com.ssafy.enjoytrip.hotplace.model.service.HotplaceService;
import com.ssafy.enjoytrip.user.User;

@RestController
@RequestMapping("/hotplace")
public class HotplaceController {
	private final Logger logger = LoggerFactory.getLogger(BoardController.class);

	private final HotplaceService hotplaceService;

	@Autowired
	public HotplaceController(HotplaceService hotplaceService) {
		this.hotplaceService = hotplaceService;
	}

	@GetMapping("")
	public ModelAndView mvHotplace() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("hotplace/hotplace");
		return modelAndView;
	}

	@GetMapping("/{hotplaceid}")
	public ResponseEntity<?> getHotplace(@PathVariable() int hotplaceid) {
		try {
			Hotplace hotplace = hotplaceService.getHotplace(hotplaceid);
			if (hotplace != null) {
				return new ResponseEntity<Hotplace>(hotplace, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	
	@GetMapping("/getHotplaceList")
	public ResponseEntity<?> getHotplaceList() {
		try {
			List<Hotplace> hotplaces = hotplaceService.listHotplace();
			if (hotplaces != null && !hotplaces.isEmpty()) {
				return new ResponseEntity<List<Hotplace>>(hotplaces, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/getRecentList")
	public ResponseEntity<?> getRecentList() {
		try {
			List<Hotplace> hotplaces = hotplaceService.listRecentHotplace();
			if (hotplaces != null && !hotplaces.isEmpty()) {
				return new ResponseEntity<List<Hotplace>>(hotplaces, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/write")
	public ResponseEntity<?> writeHotplace(@Value("${file.path.upload-files}") String filePath, Hotplace hotplace,
			@RequestParam("upfile") MultipartFile file) {
		try {

			if (!file.isEmpty()) {
				String hot = "hotplace";
				String saveFolder = filePath + hot;
				logger.debug("저장 폴더 : {}", saveFolder);
				File folder = new File(saveFolder);
				if (!folder.exists())
					folder.mkdirs();
				String originalFileName = file.getOriginalFilename();
				if (!originalFileName.isEmpty()) {
					String saveFileName = UUID.randomUUID().toString()
							+ originalFileName.substring(originalFileName.lastIndexOf('.'));
					logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", file.getOriginalFilename(), saveFileName);
					file.transferTo(new File(folder, saveFileName));
					hotplace.setImage(hot + "/" + saveFileName);
				}
			}

			hotplaceService.writeHotplace(hotplace);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PutMapping("/modify")
	public ResponseEntity<?> modifyHotplace(@RequestBody Map<String, String> map) {
		try {
			hotplaceService.modifyHotplace(map);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@DeleteMapping("/{hotplaceid}")
	public ResponseEntity<?> deleteHotplace(@PathVariable() int hotplaceid) {
		try {
			hotplaceService.deleteHotplace(hotplaceid);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
