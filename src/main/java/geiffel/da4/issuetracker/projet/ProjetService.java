package geiffel.da4.issuetracker.projet;

import geiffel.da4.issuetracker.user.User;

import java.util.List;

public interface ProjetService {

    List<Projet> getAll();

    Projet getById(long l);

    Projet create(Projet toCreate);


    void update(Projet projet);
}
