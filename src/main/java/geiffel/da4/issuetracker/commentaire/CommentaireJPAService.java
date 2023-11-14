package geiffel.da4.issuetracker.commentaire;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import geiffel.da4.issuetracker.issue.Issue;
import geiffel.da4.issuetracker.issue.IssueRepository;
import geiffel.da4.issuetracker.issue.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("jpa")
public class CommentaireJPAService  implements CommentaireService {

    @Autowired
    private CommentaireRepository commentaireRepository;
    @Override
    public List<Commentaire> getAll() {
        return commentaireRepository.findAll();
    }

    @Override
    public Commentaire getById(Long id) {
        Optional<Commentaire> commentaire = commentaireRepository.findById(id);
        if (commentaire.isPresent()) {
            return commentaire.get();
        } else {
            throw new ResourceNotFoundException("User", id);
        }
    }

    @Override
    public List<Commentaire> getAllByAuthorId(Long id) {
        return null;
    }

    @Override
    public List<Commentaire> getAllByIssueCode(Long code) {
        return null;
    }

    @Override
    public Commentaire create(Commentaire commentaire6) {
        Long id = commentaire6.getId();
        if(commentaireRepository.existsById(id)){
            throw new ResourceAlreadyExistsException("User",id);
        }else{
            return commentaireRepository.save(commentaire6);
        }
    }

    @Override
    public void update(Long id, Commentaire toUpdate1) {
        Optional<Commentaire> updatecommentaire = commentaireRepository.findById(id);
        if (updatecommentaire.isPresent()){
            Commentaire commentaire = updatecommentaire.get();
            commentaireRepository.delete(commentaire);
            commentaireRepository.save(toUpdate1);
        }else {
            throw new ResourceNotFoundException("User",id);
        }
    }

    @Override
    public void delete(Long id) {
        if (commentaireRepository.existsById(id)){
            commentaireRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("User",id);
        }
    }
}
