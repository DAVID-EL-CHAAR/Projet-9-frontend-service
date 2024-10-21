package com.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.front.model.Note;
import com.front.service.NoteService;


import java.util.List;



@Controller
@RequestMapping("/ui/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    
    @GetMapping("/patient/{patientId}")
    public String getNotesByPatient(@PathVariable String patientId, Model model) {
        List<Note> notes = noteService.getNotesByPatient(patientId);
        model.addAttribute("notes", notes);
        model.addAttribute("patientId", patientId);
        return "notes/patient-notes";
    }
    
    
    
    @GetMapping("/add/{patientId}")
    public String showAddNoteForm(@PathVariable String patientId, Model model) {
        model.addAttribute("note", new Note());
        model.addAttribute("patientId", patientId);
        return "notes/add-note";
    }

    @PostMapping("/add")
    public String addNote(@ModelAttribute Note note, @RequestParam String patientId) {
        note.setPatientId(patientId);
        noteService.addNote(note);
        return "redirect:/ui/notes/patient/" + patientId;
    }
    
    @GetMapping("/edit/{id}")
    public String showEditNoteForm(@PathVariable String id, Model model) {
        Note note = noteService.getNoteById(id);
        model.addAttribute("note", note);
        model.addAttribute("patientId", note.getPatientId());  
        return "notes/edit-note";  // Template pour modifier la note
    }

    // Méthode pour modifier une note
    @PostMapping("/update/{id}")
    public String updateNote(@PathVariable String id, @ModelAttribute Note note) {
        noteService.updateNote(id, note);
        return "redirect:/ui/notes/patient/" + note.getPatientId();  // Redirige vers la liste des notes du patient
    }
    
   
    
    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable String id, @RequestParam String patientId) {
        noteService.deleteNoteById(id);  // Appelle le service pour supprimer la note
        return "redirect:/ui/notes/patient/" + patientId;  // Redirige vers la liste des notes du patient
    }
}
