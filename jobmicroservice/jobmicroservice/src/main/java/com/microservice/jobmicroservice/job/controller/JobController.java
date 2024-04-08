package com.microservice.jobmicroservice.job.controller;


import com.microservice.jobmicroservice.job.dto.JobDTO;
import com.microservice.jobmicroservice.job.model.Job;
import com.microservice.jobmicroservice.job.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/jobs")
public class JobController {

    private final JobService jobs;

    public JobController(JobService jobs) {
        this.jobs = jobs;
    }


//resolve 2:

 /*   @Autowired
    private JobService jobs;*/

    //method 1,resolve 1:
//private List<Job> jobs = new ArrayList<>();

/*@GetMapping("/jobs")
    public List<Job> findAll(){
        return jobs;
    }
    @PostMapping("/jobs")
    public String createJob(@RequestBody Job job){
    jobs.add(job);
    return "create job is successfully!!";
    }*/

   /* @GetMapping("/jobs")
    public List<Job> findAll() {
        return jobs.findAll();
    }*/

  /*  @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll() {
        return new ResponseEntity<>(jobs.findAll(),HttpStatus.OK);
    }*/

/*    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll() {
        return  ResponseEntity.ok(jobs.findAll());
    }*/
@GetMapping("/jobs")
public ResponseEntity<List<JobDTO>> findAll() {
    return  ResponseEntity.ok(jobs.findAll());
}





 /*   @PostMapping("/jobs")
    public String createJob(@RequestBody Job job) {
        jobs.createJob(job);
        return "create job is successfully!!";
    }*/

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobs.createJob(job);
      //  Company companyAddToJob = job.getCompany();
        return new ResponseEntity<>("create job is successfully!!",HttpStatus.CREATED);
    }




  /*  @GetMapping("/jobs/{id}")
    public Job getJobById(@PathVariable Long id){

        Job job = jobs.getById(id);
        if (job != null){
            return job;
        }
        return new Job(1L,"milad","java dev"
                ,"334r","666r","shiraz")
    }*/


/*    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){

        Job job = jobs.getById(id);
        if (job != null){
            return new ResponseEntity<>(job,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/

    @GetMapping("/jobs/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id){
        JobDTO jobDTO = jobs.getById(id);
        if (jobDTO != null){
            return new ResponseEntity<>(jobDTO,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




@DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean delete = jobs.deleteJob(id);
        if (delete){
            return new ResponseEntity<>("job deleted successfully!",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
//@PutMapping("/jobs/{id}")
    @RequestMapping(value = "/jobs/{id}" ,method = RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable Long id,@RequestBody Job jobUpdate){
        boolean updatedJob = jobs.updateJob(id,jobUpdate);
        if (updatedJob){
            return new ResponseEntity<>("job updated successfully!",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }





}
