package com.mbc.nol.board;

public class BoardDTO {
		int seq;   //�� ��ȣ
	    String title;  //�� ����
	    String content;  //��������
	    int hit; //��ȸ��
	    
		public BoardDTO() {}
		public BoardDTO(int seq, String title, String content, String regdate, int hit) {
			super();
			this.seq = seq;
			this.title = title;
			this.content = content;
			this.hit = hit;
		}
		public int getSeq() {
			return seq;
		}
		public void setSeq(int seq) {
			this.seq = seq;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}

		public int getHit() {
			return hit;
		}
		public void setHit(int hit) {
			this.hit = hit;
		}
	
	
	
}
