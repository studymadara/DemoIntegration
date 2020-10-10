package com.integration.bundle.service;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import org.apache.http.HttpResponse;
import org.apache.xpath.operations.Mult;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService
{
    public Set<String> readFile(MultipartFile multipartFile)
    {
        try
        {
            Set<String> mailMergeKeys=new HashSet<>();
            Document document = new Document();
            document.loadFromStream(multipartFile.getInputStream(), FileFormat.Docx);
            String[] groupNames =document.getMailMerge().getMergeFieldNames();
            for (String keyGroup:groupNames)
            {
                mailMergeKeys.add(keyGroup);
            }
            return mailMergeKeys;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return Collections.EMPTY_SET;
        }
    }

    public void mergeValueInFile(Map<String,String> values, HttpSession httpSession, HttpServletResponse httpResponse)
    {
        try
        {
            OutputStream outputStream=httpResponse.getOutputStream();
            Document document = new Document();
            document.loadFromStream(((MultipartFile) httpSession.getAttribute("file")).getInputStream(), FileFormat.Docx);

            String[] mailMergerKeys=values.keySet().toArray(new String[values.keySet().size()]);
            String[] mailMergerValues=values.values().toArray(new String[values.values().size()]);

            document.getMailMerge().execute(mailMergerKeys,mailMergerValues);

            document.saveToFile(outputStream,FileFormat.Docx);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
