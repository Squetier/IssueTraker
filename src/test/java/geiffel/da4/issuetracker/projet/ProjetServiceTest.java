package geiffel.da4.issuetracker.projet;

import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import geiffel.da4.issuetracker.user.Fonction;
import geiffel.da4.issuetracker.user.User;
import geiffel.da4.issuetracker.user.UserLocalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ProjetServiceTest {
        private ProjetService projetService;

        private List <Projet> projets = new ArrayList<>(){{
            add(new Projet(1L,"Projet 1"));
            add(new Projet(2L,"Projet 2"));
            add(new Projet(3L,"Projet 3"));
        }};

        @BeforeEach
        void setUp(){
            projetService = new ProjetLocalService(projets);
        }

        @Test
        void whenGetAll_ShouldGet3 (){
            List <Projet> projets = projetService.getAll();
            assertEquals(3,projets.size());
        }

        @Test
        void whenGettingById_shouldReturnIfExists_andBeTheSame() {
            assertAll(
                    () -> assertEquals(projets.get(0), projetService.getById(1L)),
                    () -> assertEquals(projets.get(2), projetService.getById(3L)),
                    () -> assertThrows(ResourceNotFoundException.class, () -> projetService.getById(12L)),
                    () -> assertThrows(ResourceNotFoundException.class, () -> projetService.getById(4L))
            );
        }

        @Test
        void whenCreating_ShouldReturnSame() {
            Projet projet = new Projet(5L, "Trucmuche");
            assertEquals(projet, projetService.create(projet));

        }


        @Test
        void whenCreatingWithSameId_shouldReturnEmpty() {
            Projet projet = projets.get(0);
            assertThrows(ResourceAlreadyExistsException.class, ()->projetService.create(projet));
        }

    @Test
    void whenUpdating_shouldModifyUser() {
            Projet projetupdate = projets.get(0);
            String nouveauNom = "test";
            projetupdate.setNom(nouveauNom);

            projetService.update(projetupdate);

            Projet projetModifierRecuperer = projetService.getById(projetupdate.getId());

            assertEquals(projetupdate, projetModifierRecuperer);
    }

    @Test
    void whenUpdatingNotExistingProjet_shouldThrowException(){
            Projet new_projet = new Projet(90L,"Test2");

            assertThrows(ResourceNotFoundException.class, ()->projetService.update(new_projet));
    }









}
