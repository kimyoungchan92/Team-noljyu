package com.mbc.nol.board;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BoardService {

	// �α����� ������ �� �� ��� ��ȸ 
	List<BoardDTO> getpostsByUser(@Param("id") String loginId);
	//���� �� ��ȸ
	BoardDTO getpost(@Param("seq")int seq);
    //�ۼ���
	void updatePost(BoardDTO dto);
    //�ۻ���
	void deletePost(@Param("seq")int seq);

}
