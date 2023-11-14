package geiffel.da4.issuetracker.projet;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import geiffel.da4.issuetracker.user.User;
import geiffel.da4.issuetracker.utils.LocalService;

import java.util.List;

public class ProjetLocalService extends LocalService<Projet,Long> implements ProjetService {


    public ProjetLocalService(List<Projet> theValues){
        super(theValues);
    }

    @Override
    protected String getIdentifier() {
        return "id";
    }

    @Override
    public List<Projet> getAll() {
        return this.allValues;
    }

    @Override
    public Projet getById(long id) {
        return this.getByIdentifier(id);
    }

    @Override
    public Projet create(Projet projet) {
        try{
            this.findByProperty(projet.getId());
            throw new ResourceAlreadyExistsException("User",projet.getId());
        }catch (ResourceNotFoundException e){
            this.allValues.add(projet);
            return projet;
        }

    }

    @Override
    public void update(Projet projet_a_modifier) {
        for(Projet leProjet : this.allValues){
            if(projet_a_modifier.getId() == leProjet.getId()){
                leProjet=projet_a_modifier;
                break;
            }else throw new ResourceNotFoundException("Projet", projet_a_modifier.getId());
        }
    }


}
