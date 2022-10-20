// package com.example.starter.schedule;

// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Service;

// import com.example.starter.service.UserService;

// import lombok.extern.log4j.Log4j2;

// @Service
// @Log4j2
// public class UserSchedule {

// private final UserService userService;

// public UserSchedule(UserService userService) {
// this.userService = userService;
// }

// // 1 => Secound
// // 2 => Minutes
// // 3 => Hour
// // 4 => day
// // 5 => month
// // 6 => Year
// // Every minutes
// @Scheduled(cron = "0 * * * * *")
// public void testEveryMinutes() {
// log.info("Hello, what's up?");
// }

// // Every day at 00:00
// @Scheduled(cron = "0 0 0 * * *")
// public void testEveryMidnight() {
// log.info("Hello, what's up?");
// }

// // Every day at 10:50 AM
// @Scheduled(cron = "0 47 9 * * *")
// public void testEveryDayTenAM() {
// log.info("Hello, what's up? 9");
// }
// }
