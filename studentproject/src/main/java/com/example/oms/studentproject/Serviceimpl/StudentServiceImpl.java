package com.example.oms.studentproject.Serviceimpl;


import com.example.oms.studentproject.Model.Student;
import com.example.oms.studentproject.Model.request.StudentRequest;
import com.example.oms.studentproject.Projection.Projection;
import com.example.oms.studentproject.Repository.StudentRepository;
import com.example.oms.studentproject.Service.StudentService;
import com.example.oms.studentproject.Serviceimpl.Factory.MimeMessageHelperFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;
   // String filePath = "C:\\Users\\OMS-DESKTOP\\Downloads";

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private MimeMessageHelperFactory helperFactory;



    @Override
    public Object SaveOrUpdate(StudentRequest studentRequest) throws Exception {

//         List<Student> allStudent = studentRepository.findAll();
//        Stream<Student> str1 = allStudent.stream();
//       boolean value = str1.anyMatch(student -> student.getStudentId().equals(studentRequest.getStudentId()));
          //.collect(Collectors.toList());


        if (studentRepository.existsById(studentRequest.getStudentId())){
       // if(value){
            Student student = studentRepository.findById(studentRequest.getStudentId()).get();
            student.setStudentId(studentRequest.getStudentId());
            student.setContact(studentRequest.getContact());
            student.setEmail(studentRequest.getEmail());
            student.setLocation(studentRequest.getLocation());
            student.setName(studentRequest.getName());
            String hashedPassword = hashPassword(studentRequest.getPassword());
            student.setPassword(hashedPassword);

           // student.setPassword(studentRequest.getPassword());
            student.setUserName(studentRequest.getUserName());

            studentRepository.save(student);
            return "student updated";
        } else {
            Student student = new Student();
            String hashedPassword = hashPassword(studentRequest.getPassword());

            student.setStudentId(studentRequest.getStudentId());
            student.setPassword(hashedPassword);
          //  student.setPassword(studentRequest.getPassword());
            student.setName(studentRequest.getName());
            student.setLastName(studentRequest.getLastName());
            student.setEmail(studentRequest.getEmail());
            student.setLocation(studentRequest.getLocation());
            student.setContact(studentRequest.getContact());
            student.setUserName(studentRequest.getUserName());
            student.setIsActive(true);
            student.setIsDeleted(false);

//            Stream<Student> str = Stream.of(student);
//
//            str.forEach(st -> studentRepository.save(st));
           // String filePath = "E:\\Animesh\\myFolder";
            //String to = "animesh3511@gmail.com";
          //  String[]cc ={"animeshsuryawanshi0@gmail.com"};
            // String subject = "inside subject";
            //String body = "inside body";

          //  MultipartFile[] files = convertToMultipart(filePath);

           // this.sendEmail(files,"animesh3511@gmail.com",cc,"inside subject","inside body");

           //this.sendEmail1("animesh3511@gmail.com","hello world","inside subject","inside body");

          //  String attachmentPath = "E:\\Animesh\\myFolder\\omlogo.jpg";

           // this.sendEmailWithAttachment("animeshsuryawanshi0@gmail.com", "animeshsurya1.omsoftware@gmail.com", "inside subject", "inside body", "E:\\Animesh\\myFolder\\omlogo.jpg");

          //  String otpValue = generateOtp();
          //  Context context = new Context();
          //  context.setVariable("otp",otpValue);
         //   context.setVariable("name",studentRequest.getName());
         //   context.setVariable("message","this is email template");
        //    this.sendEmailTemplate("animesh3511@gmail.com","inside subject","emailTemplate",context);


            studentRepository.save(student);
            

            return "student saved";


        }


    }

    private MultipartFile[] convertToMultipart(String filePath) {

        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();
        MultipartFile[] files = new MultipartFile[listOfFiles.length];

        for(int i=0;i<listOfFiles.length;i++)
        {
         if(listOfFiles[i].isFile())
         {
           try{

               FileInputStream input = new FileInputStream(listOfFiles[i]);
               files[i] = new MockMultipartFile("file",listOfFiles[i].getName(),"text/plain",input);

           }catch (Exception e)
           {

               e.printStackTrace();

           }


         }


        }

        return files;
    }

    @Override
    public Object getAllRecords() {

        return studentRepository.findAll();

    }




    @Override
    public Object findById(Long studentId) throws Exception {
        return studentRepository.findById(studentId).orElseThrow(()->new RuntimeException("USER NOT FOUND")) ;


           /* List<Student> allStudents = studentRepository.findAll();
            Stream<Student> str = allStudents.stream();
            List<Student> studentWithId = str.filter(student->student.getStudentId().equals(studentId))
                .collect(Collectors.toList());
            return studentWithId;*/

        }

//
//        Optional<Student> studentOptional = studentRepository.findById(studentId);
//        if (studentOptional.isPresent()) {
//            return studentOptional.get();
//        } else {
//            throw new Exception("student not found");
//        }





    @Override
    public Object deleteById(Long studentId) {

//        Optional.of(studentId)
//                .filter(id -> studentRepository.existsById(id))
//                .ifPresent(id -> studentRepository.deleteById(id));
//
//        if (!studentRepository.existsById(studentId)) {
//            throw new RuntimeException("Student not found");
//        }
        if(studentRepository.existsById(studentId))
        {
           Student student = studentRepository.findById(studentId).get();



                studentRepository.delete(student);


        return "student deleted succesfully";

        }
        else
        {

         return "student not found";


        }
       // return "student deleted succesfully";
    }



    @Override
    public Object statusChange(Long studentId) throws Exception {

//        return studentRepository.findById(studentId)
//                .map(student -> {
//                    student.setIsActive(!student.getIsActive());
//                    studentRepository.save(student);
//                    return student.getIsActive() ? "student is active" : "student is not active";
//                })
//                .orElseThrow(() -> new Exception("student not found"));




        if(studentRepository.existsById(studentId))
        {

            Student student = studentRepository.findById(studentId).get();

            if (student.getIsActive())
            {
                student.setIsActive(false);
                studentRepository.save(student);
                return "student is not active";
            }
            else
            {
                student.setIsActive(true);
                return "student is active";

            }



        }
        else
        {

            throw new Exception("student not found");


        }




    }

    @Override
    public Object searchByName(String name, Pageable pageable) {

    //    return (name != null && !name.isEmpty()) ? studentRepository.findByName(name, pageable) : studentRepository.findAll();


    if(name!=null && !name.isEmpty())
    {

        Page<Student> studentPage =studentRepository.findByName(name,pageable);
        return studentPage;
        //here we are returning student object wrapped inside pageable object

    }
    else
    {

        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage;
        //here we are returning student objects wrapped inside pageable object

    }




    }

    @Override
    public Object searchByLocation(Pageable pageable, String location) {

    return (location != null && !location.isEmpty())?studentRepository.findByLocation(pageable, location):studentRepository.findAll(pageable);

//        if(location != null && !location.isEmpty())
//        {
//
//            return studentRepository.findByLocation(pageable,location);
//
//        }
//        else
//        {
//
//          return studentRepository.findAll(pageable);
//        }


    }

    @Override
    public Object searchByFirstNameAndLastName(Pageable pageable, String userName) {

 return  (userName!=null && !userName.isEmpty())?studentRepository.searchByFirstNameAndLastName(userName, pageable):studentRepository.findAll(pageable);

//        if(userName!=null && !userName.isEmpty())
//        {
//
//            return studentRepository.searchByFirstNameAndLastName(userName,pageable);
//
//        }
//        else
//        {
//
//            return studentRepository.findAll(pageable);
//
//        }
//


    }

    @Override
    public Object getByProjection(Pageable pageable) {


         //Page<Projection> byProjection = studentRepository.findByProjection(pageable);
        //return byProjection;

         return studentRepository.findByProjection(pageable);
        // return byProjection;

    }



    @Value("${spring.mail.username}")
    private String fromEmail;
    public String getFromEmail() {
        return fromEmail;
    }
    @Autowired
    private JavaMailSender javaMailSender;



    @Override
    public Object sendEmail(MultipartFile[] file, String to, String[] cc, String subject, String body) throws Exception {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
           MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(fromEmail);
           mimeMessageHelper.setTo(to);
            mimeMessageHelper.setCc(cc);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);

            for(int i=0;i<file.length;i++)
            {

             mimeMessageHelper.addAttachment(

                 file[i].getOriginalFilename(),
                 new ByteArrayResource(file[i].getBytes())

             );

            }
            javaMailSender.send(mimeMessage);
            return "Mail sent successfully";

        }catch(Exception e)
        {

           throw new Exception(e);

        }

    }




    public void sendEmail1(String to,String cc,String subject,String body)
    {
        SimpleMailMessage msg = new SimpleMailMessage();
       msg.setFrom(fromEmail);
       msg.setTo(to);
       msg.setSubject(subject);
       msg.setText(body);
       javaMailSender.send(msg);

      /// return "mail sent succesfully";
    }

    public void sendEmailWithAttachment(String to,String cc,String subject,String body,String attachment) throws MessagingException {
      MimeMessage mimeMessage = javaMailSender.createMimeMessage();
      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
       // MimeMessageHelper mimeMessageHelper = helperFactory.createMimeMessageHelper(mimeMessage,true);



        mimeMessageHelper.setFrom(fromEmail);
      mimeMessageHelper.setTo(to);
      mimeMessageHelper.setCc(cc);
      mimeMessageHelper.setSubject(subject);
      mimeMessageHelper.setText(body);

      //new File() creates object of class File. this object represents file at a specified path
      //it does not actually read or write the file but it just represents the file path
      //FileSystemResource is a class in spring boot that is used to wrap the file's object
      //it provides resource representation of the file's resource.it provides methods to access
      //contents of file,to retrieve file name and other properties
      FileSystemResource fileSystemResource = new FileSystemResource(new File("E:\\Animesh\\myFolder\\omlogo.jpg"));
      //here , we used getFilename() method of fileSystemResource and the fileSystemResource
        //represents that logo itself
      mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);
      javaMailSender.send(mimeMessage);



    }



    public void sendEmailTemplate(String to, String subject, String templateName, Context context) throws MessagingException {

     MimeMessage mimeMessage = javaMailSender.createMimeMessage();
     MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true,"utf-8");

     try{

         mimeMessageHelper.setFrom(fromEmail);
         mimeMessageHelper.setTo(to);
         mimeMessageHelper.setSubject(subject);


  //here, templateEngine.process processes email template which is named as "emailTemplate"
  //'emailTemplate.html' hya html template mdhe je ki templates hya package mdhe define kely
  //kahi placeholders ahet. in this case "name" & "message" hyana value Context chya object mdhun detat
         String htmlContent = templateEngine.process("emailTemplate",context);
         mimeMessageHelper.setText(htmlContent,true);

         //ithun pudh PDF sending ch logic ahe


         //ha ek java class ahe. jo kahi PDF content create hoel tyacha binary data hold krnyasathi
         //hya class chya object cha upyog hoto.tyasathi hya class cha object memory mdhe ek buffer
         //provide krto
         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

         //this class is provided by itext library
         ITextRenderer iTextRenderer = new ITextRenderer();

         //this is method of ITextRenderer class. this takes String argument which contains HTML data
         //je vrti aapn .process method n process kely. & hi method hi String arg as a 'document' set
         //krte je ki PDF mdhe render krtat
         iTextRenderer.setDocumentFromString(htmlContent);

         //this method calculates the layout of the document. this step in neccessary before creating
         //PDF to ensure that all elements are properly positioned and sized
         iTextRenderer.layout();

         //this method generates PDF from document.you can see 'byteArrayOutputSream' is the arg here
         //bcoz ji PDF generate hoel tyache content hy 'byteArrayOutputStream' mdhe write kele jatil
         //bcoz ha 'byteArrayOutputStream' memory mdhe buffer provide krto tyat he PDF che write kele
         //jatat
         iTextRenderer.createPDF(byteArrayOutputStream);

         mimeMessageHelper.setText("PDF",true);

         //'byteArrayOutputStream.toByteArray()' hi method o/p stram che contents 'byte array' mdhe convert
         //krte. so that aapn te 'addAttachment()' la arg mhnun pass kru shaku. ha 'byte array' generated
         //PDF cha binary data represent krto
         mimeMessageHelper.addAttachment("document.pdf",new ByteArrayResource(byteArrayOutputStream.toByteArray()));


         javaMailSender.send(mimeMessage);


     }catch (Exception e)
     {

      e.printStackTrace();
     }

    }


private String generateOtp()
{
    Random random = new Random();
    int otp = random.nextInt(999999);
    return String.valueOf(otp);

}

    @Override
    public Object changePassword(Long studentId, String oldPassword, String newPassword) {



        if(studentId != 0)
        {


           Student student = studentRepository.findById(studentId).get();
            String dbOldPassword =  student.getPassword();

            if( dbOldPassword.equals(oldPassword))
            {
                student.setPassword(newPassword);
                studentRepository.save(student);
                return "student saved";
            }
            else
            {
                return "oldPassword is incorrect";

            }

        }
        else
        {

           return "studentId is incorrect";

        }


    }

    @Override
    public Object fileUpload(MultipartFile userFile) throws IOException {

        String originalFilename = userFile.getOriginalFilename();

        if (!userFile.isEmpty() && userFile != null)
        {
          String storagePath = "E:\\Animesh";
            Path path = Paths.get(storagePath,originalFilename);
            Files.write(path,userFile.getBytes());


        }

       return originalFilename;
    }


    public String hashPassword(String plainPassword)
{

  return BCrypt.hashpw(plainPassword,BCrypt.gensalt());


}


}
