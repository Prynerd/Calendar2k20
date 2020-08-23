//TODO: implement DBinitializer for refactor testing
//package com.calendar.bootstrap;
//
//import com.calendar.repository.EntryRepository;
//import com.calendar.repository.UserRepository;
//import com.calendar.requestdto.EntryDto;
//import com.calendar.requestdto.ProjectDto;
//import com.calendar.requestdto.RegistrationDto;
//import com.calendar.service.impl.EntryServiceImpl;
//import com.calendar.service.impl.UserServiceImpl;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import javax.transaction.Transactional;
//import java.time.LocalDateTime;
//
//@Component
//public class DBInit  implements ApplicationListener<ContextRefreshedEvent> {
//
//    private UserRepository userRepository;
//    private EntryRepository entryRepository;
//    private PasswordEncoder passwordEncoder;
//    private UserServiceImpl userService;
//    private EntryServiceImpl entryService;
//
//
//    @Override
//    @Transactional
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//
//        try {
//            if (userRepository.findAll().size() == 0) {
//                loadUsers();
//            }
//        } catch (NullPointerException ex) {
//            loadUsers();
//        }
//
//        try {
//            if (entryRepository.findAll().size() == 0) {
//                loadEntries();
//            }
//        } catch (NullPointerException ex) {
//            loadEntries();
//        }
//    }
//
//    private void loadUsers() {
//        RegistrationDto registrationDto = new RegistrationDto();
//        registrationDto.setEmail("admin@admin.com");
//        registrationDto.setPassword("Test001");
//
//        userService.createUser(registrationDto);
//    }
//
//    private void loadEntries() {
//        //Create first project
//        ProjectDto projectDto = new ProjectDto();
//        projectDto.setTitle("testproject");
//        projectDto.setDescription("First Project");
//        projectDto.setTermin(LocalDateTime.now());
//
//        entryService.createProject(projectDto);
//
//        //Create first child-entry
//        EntryDto entryDto = new EntryDto();
//        entryDto.setAddedEntryId(1);
//        entryDto.setTitle("First child");
//        entryDto.setDescription("Whatever");
//        entryDto.setDate(LocalDateTime.now());
//        entryDto.setDuration(LocalDateTime.now().plusDays(2));
//        entryDto.setTermin(LocalDateTime.now().plusDays(2));
//        entryDto.setEntryType("EVENT");
//        entryDto.setEntryPhase("UNSTARTED");
//
//        entryService.createEntry(entryDto);
//
//
//
//    }
//}
