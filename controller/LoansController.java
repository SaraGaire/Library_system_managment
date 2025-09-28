

import com.example.library.model.Loan;
import com.example.library.repo.LoanRepository;
import com.example.library.service.LoanService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/loans")
@CrossOrigin(origins = "*")
public class LoansController {
private final LoanService service;
private final LoanRepository loans;
public LoansController(LoanService s, LoanRepository l) { this.service = s; this.loans = l; }


record BorrowRequest(String universityId, String bookId) {}


@PostMapping("/borrow")
public Loan borrow(@RequestBody BorrowRequest r) { return service.borrow(r.universityId(), r.bookId()); }


@PostMapping("/{id}/renew")
public Loan renew(@PathVariable String id) { return service.renew(id); }


@PostMapping("/{id}/return")
public void returnBook(@PathVariable String id) { service.returnBook(id); }


@GetMapping("/member/{memberId}")
public List<Loan> byMember(@PathVariable String memberId) { return loans.findByMemberIdAndReturnedFalse(memberId); }
}
