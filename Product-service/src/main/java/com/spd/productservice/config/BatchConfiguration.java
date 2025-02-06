//package com.spd.productservice.config;
//
//import com.spd.productservice.entity.Product;
//import com.spd.productservice.repository.ProductRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.data.MongoItemWriter;
//import org.springframework.batch.item.data.RepositoryItemWriter;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.LineMapper;
//import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.core.io.Resource;
//import org.springframework.data.mongodb.MongoTransactionManager;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.Base64;
//
//@Configuration
//@EnableBatchProcessing
//public class BatchConfiguration {
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Value("#{jobParameters['filePath']}")
//    private static String file;
//
//    @Bean
//    public FlatFileItemReader<Product> reader() {
//        byte[] decodedBytes = Base64.getDecoder().decode(file);
//        FlatFileItemReader<Product> reader = new FlatFileItemReader<>();
//        reader.setResource(new FileSystemResource(file));
//        reader.setName("csvReader");
//        reader.setLinesToSkip(1);
//        reader.setLineMapper(lineMapper());
//        return reader;
//    }
//
//    @Bean
//    public ProductProcesser productProcesser() {
//        return new ProductProcesser();
//    }
//
//    @Bean
//    public MongoItemWriter<Product> writer(MongoTemplate mongoTemplate) {
//        MongoItemWriter<Product> writer = new MongoItemWriter<>();
//        writer.setCollection("products");
//        writer.setTemplate(mongoTemplate);
//        return writer;
//    }
//
//    @Bean
//    public Job job(JobRepository jobRepository, Step step) {
//        return new JobBuilder("importProductData", jobRepository)
//                .start(step).build();
//    }
//
//    @Bean
//    public Step step(JobRepository jobRepository,MongoItemWriter<Product> writer) {
//        return new StepBuilder("importProductData", jobRepository)
//                .chunk(100)
//                .processor(productProcesser())
//                .writer((writer)
//                .build();
//    }
//
//    private LineMapper<Product> lineMapper() {
//        DefaultLineMapper<Product> lineMapper = new DefaultLineMapper<>();
//        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
//        lineTokenizer.setDelimiter(",");
//        lineTokenizer.setStrict(false);
//        lineTokenizer.setNames("PRODUCT_ID","NAME","PRICE","QUANTITY","DESCRIPTION","CATEGORY_ID");
//        BeanWrapperFieldSetMapper<Product> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
//        fieldSetMapper.setTargetType(Product.class);
//        lineMapper.setLineTokenizer(lineTokenizer);
//        lineMapper.setFieldSetMapper(fieldSetMapper);
//        return lineMapper;
//    }
//
//}
