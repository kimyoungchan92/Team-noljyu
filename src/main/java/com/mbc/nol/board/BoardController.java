package com.mbc.nol.board;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private SqlSession sqlSession;
	
	//���� �� ����
	@RequestMapping(value = "/modifyForm")
	public String modifyForm(@RequestParam int seq, Model model) {
		BoardService bs= sqlSession.getMapper(BoardService.class);
		BoardDTO post = bs.getpost(seq);
		model.addAttribute("post", post);
		return "board/modifyForm";
	}
	
	//����ó��
	@RequestMapping(value = "/update")
	public String update(@ModelAttribute BoardDTO dto, HttpSession session) {
		BoardService bs = sqlSession.getMapper(BoardService.class);
		bs.updatePost(dto);
		session.setAttribute("msg", "���� �����Ǿ����ϴ�.");
		return "redirect:/mypage";
	}

	//����ó��
	@RequestMapping(value = "/delete")
	public String delete(@RequestParam int seq, HttpSession session) {
		BoardService bs = sqlSession.getMapper(BoardService.class);
		bs.deletePost(seq);
		session.setAttribute("msg", "���� �����Ǿ����ϴ�.");
		return "redirect:/mypage";
	}
}
