package com.example.java_test;

public class Study {

	private StudyStatus studyStatus = StudyStatus.DRAFT;
	private int limit;

	public Study(int limit) {
		if (limit < 0) {
			throw new IllegalArgumentException("limit은 0보다 커야합니다");
		}

		this.limit = limit;
	}

	public StudyStatus getStudyStatus() {
		return studyStatus;
	}

	public int getLimit() {
		return limit;
	}
}
