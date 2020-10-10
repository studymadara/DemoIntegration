package com.integration.bundle.controller;

import com.integration.bundle.service.FileService;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.Map;

@Controller
public class FileController
{
    //steps
    //1.get file from user
    //2.read the file from user
    //3.render page based on the data user uploaded
    //4.now user will submit this new page filled with values
    //5.get that data now fill that in the uploaded file #mostly session scope should help me


    @Autowired
    FileService fileService;

    @GetMapping("/uploadData")
    public ModelAndView showPage()
    {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("uploadPage");
        return modelAndView;
    }

    //1.get file from user
    //2.read the file from user
    //3.render page based on the data user uploaded
    @PostMapping("/docxUpload")
    public @ResponseBody Object docxUpload(@RequestParam("file") MultipartFile multipartFile, HttpSession httpSession)
    {
        httpSession.setAttribute("file",multipartFile);
        return fileService.readFile(multipartFile);
    }

    @PostMapping(value = "/generateFile",consumes = MediaType.ALL_VALUE)
    public void generateFile(@RequestParam  MultiValueMap<String,String> data, HttpSession httpSession, HttpServletResponse httpResponse)
    {
        httpResponse.setHeader("Content-disposition","attachment; filename=RESULT.docx");
        httpResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        fileService.mergeValueInFile(data.toSingleValueMap(),httpSession,httpResponse);
    }
}
