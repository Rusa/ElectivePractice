package com.epam.electives.controller;

import com.epam.electives.dto.GetEntityRequest;
import com.epam.electives.dto.PageDto;
import com.epam.electives.model.Course;
import com.epam.electives.services.CourseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class MainController  {

    @Autowired
    CourseMainService courseMainService;

    @RequestMapping("/")
    public ModelAndView startPage(){
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }

    @RequestMapping("/courses.main")
    public ModelAndView courses(@RequestParam int n){
        ModelAndView modelAndView = new ModelAndView("courses.main");
        modelAndView.addObject("courses", courseMainService.getN(n));
        return modelAndView;
    }

    @RequestMapping("/all")
    public ModelAndView coursesAll(){
        ModelAndView modelAndView = new ModelAndView("all");
        modelAndView.addObject("listCourses", courseMainService.getAll());
        return modelAndView;
    }

    @RequestMapping(value="/newcourse", method=RequestMethod.POST)
    public ModelAndView addCourse(@RequestParam("courseName") String courseName) {
        Course course = new Course();
        course.setName(courseName);
        courseMainService.saveOrUpdate(course);
        return coursesAll();
    }

    @RequestMapping(value="/editcourse", method=RequestMethod.POST)
    public ModelAndView editCourse(@RequestParam long id) {
        Course course = courseMainService.getById(id);
        ModelAndView modelAndView = new ModelAndView("editcourse");
        modelAndView.addObject("courseName", course.getName());
        modelAndView.addObject("courseId", course.getId());
        return modelAndView;
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST)
    public ModelAndView updateCourse(@RequestParam long id, @RequestParam String courseName) {
        Course course = courseMainService.getById(id);
        course.setName(courseName);
        course.setUpdateDate(new Date());
        courseMainService.saveOrUpdate(course);
        return coursesAll();
    }

    @ResponseBody
    @RequestMapping(value = "/news.all", method = RequestMethod.POST)
    public PageDto<Course> mainAllNews(@RequestBody GetEntityRequest request){
//        ModelAndView modelAndView = new ModelAndView("course.main");
//        modelAndView.addObject("course", courseMainService.getPart(request));
        PageDto<Course> courses = courseMainService.getPart(request);
        return courses;
        //return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public Course addNews(@RequestBody Course courses){

        // temp appId, later take it from HttpRequest request
//        ModelAndView modelAndView = new ModelAndView("addNews");
//        modelAndView.addObject("news", newsMainService.saveOrUpdate(news));
        courseMainService.saveOrUpdate(courses);
        return courses;
        //return modelAndView;
    }
}
