package com.moham.coursemores.service;

public interface LikeService {

    boolean checkLikeCourse(int userId, int courseId);

    void addLikeCourse(int userId, int courseId);

}