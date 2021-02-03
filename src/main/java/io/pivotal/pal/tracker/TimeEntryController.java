package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
    private final TimeEntryRepository timeEntriesRepo;

    public TimeEntryController(TimeEntryRepository timeEntriesRepo) {
        this.timeEntriesRepo = timeEntriesRepo;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        return created(null)
                .body(timeEntriesRepo.create(timeEntryToCreate));
    }

    @GetMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        var timeEntryFound = timeEntriesRepo.find(timeEntryId);
        if (timeEntryFound != null) {
            return ok(timeEntryFound);
        }
        else {
            return notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return ok(timeEntriesRepo.list());
    }

    @PutMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntryToUpdate) {
        TimeEntry timeEntry = timeEntriesRepo.update(timeEntryId, timeEntryToUpdate);
        if(timeEntry != null) {
            return ok(timeEntry);
        }
        else{
            return notFound().build();
        }
    }

    @DeleteMapping("{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable long timeEntryId) {
        timeEntriesRepo.delete(timeEntryId);
        return noContent().build();
    }
}
