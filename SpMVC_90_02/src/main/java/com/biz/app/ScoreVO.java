package com.biz.app;

public class ScoreVO {

	private int kor;
	private int eng;
	private int math;
	private int sci;
	private int music;

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getSci() {
		return sci;
	}

	public void setSci(int sci) {
		this.sci = sci;
	}

	public int getMusic() {
		return music;
	}

	public void setMusic(int music) {
		this.music = music;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getAvg() {
		return avg;
	}

	public void setAvg(int avg) {
		this.avg = avg;
	}

	private int sum;
	private int avg;

	@Override
	public String toString() {
		return "ScoreVO [kor=" + kor + ", eng=" + eng + ", math=" + math + ", sci=" + sci + ", music=" + music
				+ ", sum=" + sum + ", avg=" + avg + "]";
	}

	public ScoreVO(int kor, int eng, int math, int sci, int music, int sum, int avg) {
		super();
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.sci = sci;
		this.music = music;
		this.sum = sum;
		this.avg = avg;
	}

	public ScoreVO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
