# easylearn_data_manager

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/74dd462e985340cfa11af392ace6f670)](https://www.codacy.com/gh/nicugnm/easylearn_data_manager/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=nicugnm/easylearn_data_manager&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/74dd462e985340cfa11af392ace6f670)](https://www.codacy.com/gh/nicugnm/easylearn_data_manager/dashboard?utm_source=github.com&utm_medium=referral&utm_content=nicugnm/easylearn_data_manager&amp;utm_campaign=Badge_Grade)

EasyLearn-Data-Manager is part of EasyLearn and is the microservice that manage the data of the application.

Stack used for the project is:

* Java 17
* Spring with Spring Boot, 2.6.7
* Spring Native 0.11.4 & GraalVM Support
* DAOs
* JaCoCo Plugin 0.8.8
* Mocked Tests are maked with Mockito and JUnit 5
* Codacy for analyze, quality code and coverage tests
* Github Actions

When I writed code, I was careful to avoid
some [pitfalls](https://thorben-janssen.com/lombok-hibernate-how-to-avoid-common-pitfalls/):

- Don’t Use @EqualsAndHashCode
- Be Careful with @ToString (Not use on @OneToMany and @ManyToOne)
- Avoid @Data
- Every Entity class should implement Serializable
