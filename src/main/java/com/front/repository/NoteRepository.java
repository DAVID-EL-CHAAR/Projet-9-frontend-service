package com.front.repository;

import com.front.model.Note;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;





import java.util.Arrays;
import java.util.List;



@Repository
public class NoteRepository {

    private final RestTemplate restTemplate;

    @Value("${microservice.note-service-url}")
    private String noteServiceUrl;


    public NoteRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    

   

    
    public List<Note> getNotesByPatient(String patientId) {
        String url = noteServiceUrl + "/notes/patient/" + patientId;
        Note[] notes = restTemplate.getForObject(url, Note[].class);
        return Arrays.asList(notes);
    }

    public void addNote(Note note) {
        String url = noteServiceUrl + "/notes/add";
        restTemplate.postForObject(url, note, Note.class);
    }
    
    public void deleteNote(String id) {
        String url = noteServiceUrl + "/notes/delete/" + id;
        restTemplate.delete(url);
    }
    
    public Note getNoteById(String id) {
        String url = noteServiceUrl + "/notes/" + id;
        return restTemplate.getForObject(url, Note.class);
    }

    
    public void updateNote(String id, Note note) {
        String url = noteServiceUrl + "/notes/update/" + id;
        restTemplate.put(url, note);
    }
    
    public void deleteNoteById(String id) {
        String url = noteServiceUrl + "/notes/delete/" + id;
        restTemplate.delete(url);  // je dois Utilise RestTemplate pour envoyer la requête DELETE
    }
}
 