package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.LignePanier;
import tn.esprit.spring.entities.Panier;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.interfaces.IPanierService;
import tn.esprit.spring.repositories.ArticleRepository;
import tn.esprit.spring.repositories.LpanierRepository;
import tn.esprit.spring.repositories.PanierRepository;
import tn.esprit.spring.repositories.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class PanierImpl implements IPanierService {

    @Autowired
    PanierRepository panierRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LpanierRepository lpanierRepository;

    @Override
    public void createPanier(String userID) {
        Panier panier = new Panier();
        User user= userRepository.findByUserName(userID);
        panier.setUserId(user);
        panier.setDateUpdated(LocalDate.now());
        panier.setCoutTotal((float) 0);
        panier.setNbrArticle(0);
        panierRepository.save(panier);
    }
    @Override
    public void deletePanier(Integer panierID){

        panierRepository.deleteById(panierID);
    }


    @Override
    public Panier getPanierById(Integer PID) {
        return panierRepository.findById(PID).orElse(null);
    }

    @Override
    public Panier getPanierByUser(String userID) {
        User user=userRepository.findById(String.valueOf(userID)).orElse(null);

       return panierRepository.findByUserId(user);
    }

    @Override
    public List<LignePanier> getLignePanierByUser(String idUser){
        User user= userRepository.findById(idUser).orElse(null);
        Panier panier= panierRepository.findByUserId(user);
        List<LignePanier> lignePanierList= lpanierRepository.findByPanier(panier);
        return lignePanierList;


    }
@Override
    public List<Panier> getAllPanier() {
        List<Panier> panierList = panierRepository.findAll();
        return panierList;
    }
    @Override
    public Float getTotalPrice(Panier panier) {
        List<LignePanier> lignePaniers = lpanierRepository.findByPanier(panier);
        Float totalPrice = 0f;
        if(lignePaniers!=null) {
            for (LignePanier lignePanier : lignePaniers) {
                Float articlePrice = lignePanier.getArticle().getPrixHT();
                Integer articleQuantity = lignePanier.getQuantite();
                totalPrice += articlePrice * articleQuantity;
            }
        }else{
            totalPrice = 0f;
        }
        return totalPrice;
    }



    /** @Override
    public void deletePanier(Integer id) {
        panierRepository.deleteById(id);
    }
    public Panier addArticleToShoppingCart(User user, Article article, int quantity) {
        // Check if the user already has a shopping cart
        Panier panier = panierRepository.findPanierByUser(user);

        if (panier == null) {
            // If the user does not have a shopping cart, create a new one
            panier = new Panier();
            panier.setUserId(user);
        }

        // Create a new LineCart entity to represent the article being added
        LignePanier lignePanier = LpanierImpl.addArticleToPanier(article, quantity);

        // Add the LineCart to the shopping cart
        panier.(lineCart);

        // Save the updated shopping cart to the database
        return shoppingCartRepository.save(shoppingCart);
    }
  /**  public void addAndAssignItemToShoppingCart(Long itemId, Long userId) {
        // First, check if the item and user exist
        /**Article article = arti.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("Item not found"));
        User user = userRepository.findById(String.valueOf(userId)).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Then, check if the user already has a shopping cart
        ShoppingCart shoppingCart = shoppingCartRepository.findByUser(user)
                .orElseGet(() -> {
                    ShoppingCart newShoppingCart = new ShoppingCart();
                    newShoppingCart.setUser(user);
                    return shoppingCartRepository.save(newShoppingCart);
                });

        // Finally, add the item to the shopping cart
        LineCart lineCart = new LineCart();
        lineCart.setItem(item);
        lineCart.setShoppingCart(shoppingCart);
        lineCartRepository.save(lineCart);
    }**/

}
