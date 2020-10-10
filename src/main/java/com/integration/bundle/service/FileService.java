package com.integration.bundle.service;

import org.apache.http.HttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Set;


public interface FileService
{
    //steps
    //1.read the file >> get data
    //2.set values in file  #get file from session
    //3.maybe convert to pdf

    //1.read the file >> get data
    Set<String> readFile(MultipartFile multipartFile);
    //2.set values in file  #get file from session
    void mergeValueInFile(Map<String,String> values, HttpSession httpSession, HttpServletResponse httpResponse);


}
