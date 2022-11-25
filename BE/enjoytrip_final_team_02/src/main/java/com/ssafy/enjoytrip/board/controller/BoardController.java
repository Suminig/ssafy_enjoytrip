package com.ssafy.enjoytrip.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.enjoytrip.board.Board;
import com.ssafy.enjoytrip.board.model.service.BoardService;
import com.ssafy.enjoytrip.spot.Spot;
import com.ssafy.enjoytrip.user.User;

@RestController
@RequestMapping("/board")
public class BoardController {
	private final Logger logger = LoggerFactory.getLogger(BoardController.class);

	private final BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	@GetMapping("")
	public ModelAndView mvListSchedule() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("board/listSchedule");
		return modelAndView;
	}

	@GetMapping("/schedule")
	public ModelAndView makeSchedule() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("board/schedule");
		return modelAndView;
	}

	@GetMapping("/view")
	public ModelAndView mvViewSchedule() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("board/viewSchedule");
		return modelAndView;
	}

	@GetMapping("/write")
	public ModelAndView mvWriteSchedule() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("board/writeSchedule");
		return modelAndView;
	}

	@GetMapping("/modify")
	public ModelAndView mvModifySchedule() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("board/modifySchedule");
		return modelAndView;
	}

	@GetMapping("/top")
	public ResponseEntity<?> getTopSchedules() {
		try {
			List<Board> boards = boardService.getTopBoards();
			if (boards != null && !boards.isEmpty()) {
				return new ResponseEntity<List<Board>>(boards, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/top/{boardid}")
	public ResponseEntity<?> getTopScheduleSpots(@PathVariable int boardid) {
		try {
			List<Map<String, String>> map = boardService.getTopBoardSpots(boardid);
			if (map != null && !map.isEmpty()) {
				return new ResponseEntity<List<Map<String, String>>>(map, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/getSchedule")
	public ResponseEntity<?> getScheduleList(@RequestParam Map<String, String> map) {
		try {
			List<Board> boards = boardService.listBoard(null);
			if (boards != null && !boards.isEmpty()) {
				return new ResponseEntity<List<Board>>(boards, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/view/{boardid}")
	public ResponseEntity<?> getScheduleView(@PathVariable() int boardid) {
		try {
			Board board = boardService.getBoard(boardid);
			if (board != null) {
				return new ResponseEntity<Board>(board, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/view/schedulespot/{boardid}")
	public ResponseEntity<?> boardGetSpots(@PathVariable() int boardid) {
		try {
			List<Map<String, String>> spots = boardService.getSpots(boardid);
			if (spots != null && !spots.isEmpty()) {
				return new ResponseEntity<List<Map<String, String>>>(spots, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PutMapping("/view/countup/{boardid}")
	public ResponseEntity<?> boardCountUp(@PathVariable() int boardid) {
		try {
			boardService.updateBoardHit(boardid);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@Transactional
	@DeleteMapping("/view/{boardid}")
	public ResponseEntity<?> deleteSchedule(@PathVariable() int boardid) {
		try {
			boardService.deleteSpots(boardid);
			boardService.deleteBoard(boardid);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/writeSchedule")
	public ResponseEntity<?> writeSchedule(@RequestBody Map<String, String> map) {
		try {
			boardService.writeBoard(map);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/writeScheduleSpot")
	public ResponseEntity<?> writeScheduleSpot(@RequestBody Map<String, String> map) {
		try {
			boardService.writeScheduleSpot(map);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/writeSchedule/boardid/{uid}")
	public ResponseEntity<?> getBoardId(@PathVariable() int uid) {
		try {
			int boardId = boardService.getLastBoardid(uid);
			return new ResponseEntity<Integer>(boardId, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@Transactional
	@PutMapping("/modify")
	public ResponseEntity<?> modifySchedule(@RequestBody Map<String, String> map) {
		try {
			boardService.modifyBoard(map);
			boardService.modifySpots(map);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
