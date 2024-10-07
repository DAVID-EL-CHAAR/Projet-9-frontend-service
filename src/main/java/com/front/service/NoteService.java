package com.front.service;

import com.front.model.Note;

import com.front.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    
    public List<Note> getNotesByPatient(String patientId) {
        return noteRepository.getNotesByPatient(patientId);
    }
    
   

    public void addNote(Note note) {
        noteRepository.addNote(note);
    }
    
    public Note getNoteById(String id) {   	
    	return noteRepository.getNoteById(id);
    }
    
    public void deleteNoteById(String id) {
        noteRepository.deleteNoteById(id);
    }
    
    public void updateNote(String id, Note note) {
        noteRepository.updateNote(id, note);
    }
    

}
