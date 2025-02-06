//package com.spd.productservice.controller;
//
//import lombok.AllArgsConstructor;
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
//import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
//import org.springframework.batch.core.repository.JobRestartException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.Base64;
//
//@RestController
//@RequestMapping("/api/jobs")
//@AllArgsConstructor
//public class JobController {
//
//    private JobLauncher jobLauncher;
//    private Job job;
//
//    @PostMapping("/importProductData")
//    public ResponseEntity<?> importProductData(@RequestParam("file") MultipartFile file) throws IOException {
//
//        // Save the uploaded file to a temporary location
//        Path tempFile = Files.createTempFile("temp-products", ".csv");
//        file.transferTo(tempFile.toFile());
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addLong("startAt", System.currentTimeMillis())
//                .addString("filePath", tempFile.toAbsolutePath().toString())
//                .toJobParameters();
//        try {
//            // Launch the job
//            final JobExecution jobExecution = jobLauncher.run(job, jobParameters);
//            Files.delete(tempFile);
//            // Return job status
//            return new ResponseEntity<>(jobExecution.getStatus().toString(), HttpStatus.OK);
//        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
//                 | JobParametersInvalidException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("Job failed with exception: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
