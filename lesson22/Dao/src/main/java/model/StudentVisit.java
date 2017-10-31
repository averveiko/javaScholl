package model;

public class StudentVisit {
    private int studentId;
    private int lessonId;

    public StudentVisit() {
    }

    public StudentVisit(int studentId, int lessonId) {
        this.studentId = studentId;
        this.lessonId = lessonId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }
}
