package com.company.recipes.config;

import com.company.recipes.dao.*;
import com.company.recipes.enums.Category;
import com.company.recipes.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner{

    private RecipeDao recipeDao;
    private UserDao userDao;
    private RoleDao roleDao;

    @Autowired
    public DatabaseLoader(RecipeDao recipeDao, UserDao userDao, RoleDao roleDao){
        this.recipeDao = recipeDao;
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public void run(ApplicationArguments args) {

        Role role = new Role("ROLE_USER");
        roleDao.save(role);
        User user = new User("user", true, "password", "password");
        user.setRole(role);
        User user2 = new User("user2", true, "password", "password");
        user2.setRole(role);
        user.encryptPasswords();
        user2.encryptPasswords();
        userDao.save(user);
        userDao.save(user2);

        Recipe recipe = new Recipe("Keto Jalapeño Popper Chicken",
                "This Keto Jalapeño Popper Chicken is extraordinarily delicious, super simple, and only made with 5 ingredients.",
                Category.LUNCH, null, 10, null, 30,
                "https://static1.squarespace.com/static/592439cc9f7456571bfce32a/592456c49de4bb0ca603b9b2/5a96c703ec212d0e8ea8a40d/1537451143536/Keto+Jalapeno+Popper+Chicken_-5.jpg?format=1500w");
        user.addOwnedRecipe(recipe);
        recipe.setUser(user);
        Ingredient ingredient = new Ingredient("4 (4oz) chicken breasts");
        Ingredient ingredient2 = new Ingredient("2 jalapeños");
        Ingredient ingredient3 = new Ingredient("4 oz cream cheese");
        Ingredient ingredient4 = new Ingredient("4 oz cheddar cheese");
        Ingredient ingredient5 = new Ingredient("8 strips bacon");
        Step step = new Step("Flatten chicken out to 1/2 inch thickness. Preheat oven to 375° F.");
        Step step2 = new Step("Divide jalapeño and cheese equally between each chicken breast and roll the chicken tightly around filling.");
        Step step3 = new Step("Wrap each chicken breast with 2 slices of bacon and place in skillet or oven safe baking dish.");
        Step step4 = new Step("Bake for 30 minutes or until bacon is crispy on the outside and chicken is cooked through.");
        recipe.addIngredient(ingredient);ingredient.setRecipe(recipe);
        recipe.addIngredient(ingredient2);ingredient2.setRecipe(recipe);
        recipe.addIngredient(ingredient3);ingredient3.setRecipe(recipe);
        recipe.addIngredient(ingredient4);ingredient4.setRecipe(recipe);
        recipe.addIngredient(ingredient5);ingredient5.setRecipe(recipe);
        recipe.addStep(step);step.setRecipe(recipe);
        recipe.addStep(step2);step2.setRecipe(recipe);
        recipe.addStep(step3);step3.setRecipe(recipe);
        recipe.addStep(step4);step4.setRecipe(recipe);

        recipeDao.save(recipe);


        Recipe recipe2 = new Recipe("Easy Sweet Chilli Salmon",
                "Just ten minutes is needed to whip up this easy seared sweet chilli salmon! Perfect for meal prep and easy to throw over salads, in sandwiches or eaten as a main meal. Gluten Free, sugar free and an ideal protein option!",
                Category.DINNER, null, 30, null, 40,
                "https://thebigmansworld.com/wp-content/uploads/2015/03/sweet-chilli-salmon-3.jpg");
        user.addOwnedRecipe(recipe2);
        recipe2.setUser(user);
        Ingredient ingredient21 = new Ingredient("4 x fillets of salmon (approximately 125-150 grams each)");
        Ingredient ingredient22 = new Ingredient("Oil for searing");
        Ingredient ingredient23 = new Ingredient("1/4 cup liquid aminos (can use soy sauce)");
        Ingredient ingredient24 = new Ingredient("small handful of baby spinach, chopped very finely");
        Ingredient ingredient25 = new Ingredient("1 tsp black pepper");
        Ingredient ingredient26 = new Ingredient("2 tsp red pepper flakes (optional)");
        Ingredient ingredient27 = new Ingredient("1/4 cup homemade chili sauce (I used a whole30 homemade sauce from here)");
        Ingredient ingredient28 = new Ingredient("2 tsp sesame oil");
        Ingredient ingredient29 = new Ingredient("1 tsp liquid aminos (Can use soy sauce)");
        Step step21 = new Step("In a small bowl, make the marinade and mix well.");
        Step step22 = new Step("In a separate bowl, make the sweet chilli sauce topping. Set aside.");
        Step step23 = new Step("Coat a large frying pan with oil and heat on medium Once hot, quickly coat" +
                " the salmon fillets in the marinade (ensure it is fully coated) before adding to the pan. Sear on each side for 1-3 minutes." +
                " Remove from the pan and cover in aluminium foil for 3 minutes to rest." +
                " Evenly divide the sweet chilli sauce topping amongst the four salmon fillets.");
        recipe2.addIngredient(ingredient21);ingredient21.setRecipe(recipe2);
        recipe2.addIngredient(ingredient22);ingredient22.setRecipe(recipe2);
        recipe2.addIngredient(ingredient23);ingredient23.setRecipe(recipe2);
        recipe2.addIngredient(ingredient24);ingredient24.setRecipe(recipe2);
        recipe2.addIngredient(ingredient25);ingredient25.setRecipe(recipe2);
        recipe2.addIngredient(ingredient26);ingredient26.setRecipe(recipe2);
        recipe2.addIngredient(ingredient27);ingredient27.setRecipe(recipe2);
        recipe2.addIngredient(ingredient28);ingredient28.setRecipe(recipe2);
        recipe2.addIngredient(ingredient29);ingredient29.setRecipe(recipe2);
        recipe2.addStep(step21);step21.setRecipe(recipe2);
        recipe2.addStep(step22);step22.setRecipe(recipe2);
        recipe2.addStep(step23);step23.setRecipe(recipe2);

        recipeDao.save(recipe2);


        Recipe recipe3 = new Recipe("Garlic Shrimp with Quinoa",
                "Garlic Shrimp with Quinoa—Easy, quick, and delicious! Healthy recipe with fresh lemon and garlic. Not too spicy with lots of flavor.",
                Category.LUNCH, null, 5, null, 25,
                "https://www.wellplated.com/wp-content/uploads/2017/02/Garlic-Shrimp-with-Quinoa-Easy-Gluten-Free-500x731@2x.jpg");
        user.addOwnedRecipe(recipe3);
        recipe3.setUser(user);
        Ingredient ingredient31 = new Ingredient("4 teaspoons extra-virgin olive oil");
        Ingredient ingredient32 = new Ingredient("1 pound raw tail-on shrimp");
        Ingredient ingredient33 = new Ingredient("1 teaspoon kosher salt");
        Ingredient ingredient34 = new Ingredient("1/2 teaspoon chili powder");
        Ingredient ingredient35 = new Ingredient("1/3 cup finely chopped yellow onion");
        Ingredient ingredient36 = new Ingredient("3 cloves garlic");
        Ingredient ingredient37 = new Ingredient("1 cup uncooked Bob’s Red Mill Quinoa");
        Ingredient ingredient38 = new Ingredient("1/4 teaspoon cayenne pepper");
        Ingredient ingredient39 = new Ingredient("2 cups low-sodium chicken broth");
        Ingredient ingredient310 = new Ingredient("1 large lemon");
        Ingredient ingredient311 = new Ingredient("3 tablespoons fresh parsley");
        Step step31 = new Step("In a large nonstick skillet with a tight-fitting lid," +
                " heat 2 teaspoons of the olive oil over medium high." +
                " Add the shrimp, then sprinkle with 1/2 teaspoon salt and 1/4 teaspoon chili powder." +
                " Sauté just until the shrimp are pink and cooked through, about 3 minutes." +
                " Immediately remove the shrimp to a plate so they do not overcook.");
        Step step32 = new Step("Heat the remaining 2 teaspoons olive oil in the same skillet, then add the onion." +
                " Let cook until the onion begins to soften, about 5 minutes." +
                " Add the garlic and cook just until fragrant, about 30 seconds." +
                " Add the quinoa, cayenne, and remaining 1/2 teaspoon salt and 1/4 teaspoon chili powder." +
                " Stir to coat the quinoa with the oil and let brown for 2 minutes." +
                " Pour in the chicken stock, then increase the heat to high and bring the broth to a boil." +
                " Once boiling, cover and reduce the heat to a simmer." +
                " Let simmer until the quinoa is tender, 12 to 15 minutes. Uncover and fluff with a fork.");
        Step step33 = new Step("Zest the lemon directly into the pan," +
                " then juice the lemon and add the lemon juice and parsley to the skillet as well." +
                " Toss to combine, then top with the reserved shrimp." +
                " Sprinkle with additional fresh parsley. Serve warm.");
        recipe3.addIngredient(ingredient31);ingredient31.setRecipe(recipe3);
        recipe3.addIngredient(ingredient32);ingredient32.setRecipe(recipe3);
        recipe3.addIngredient(ingredient33);ingredient33.setRecipe(recipe3);
        recipe3.addIngredient(ingredient34);ingredient34.setRecipe(recipe3);
        recipe3.addIngredient(ingredient35);ingredient35.setRecipe(recipe3);
        recipe3.addIngredient(ingredient36);ingredient36.setRecipe(recipe3);
        recipe3.addIngredient(ingredient37);ingredient37.setRecipe(recipe3);
        recipe3.addIngredient(ingredient38);ingredient38.setRecipe(recipe3);
        recipe3.addIngredient(ingredient39);ingredient39.setRecipe(recipe3);
        recipe3.addIngredient(ingredient310);ingredient310.setRecipe(recipe3);
        recipe3.addIngredient(ingredient311);ingredient311.setRecipe(recipe3);
        recipe3.addStep(step31);step31.setRecipe(recipe3);
        recipe3.addStep(step32);step32.setRecipe(recipe3);
        recipe3.addStep(step33);step33.setRecipe(recipe3);

        recipeDao.save(recipe3);




        Recipe recipe4 = new Recipe("Sheet Pan Shrimp Fajitas",
                "These Sheet Pan Shrimp Fajitas are quick, easy and perfect for dinner on a busy night. They cook quickly and make great leftovers for lunch or dinner later in the week.",
                Category.DINNER, null, 5, null, 15,
                "https://www.theleangreenbean.com/wp-content/uploads/2018/02/Sheet-Pan-Shrimp-Fajitas-3.jpg");
        user2.addOwnedRecipe(recipe4);
        recipe4.setUser(user2);
        Ingredient ingredient41 = new Ingredient("juice from one lime");
        Ingredient ingredient42 = new Ingredient("2 tsp oil (i used olive oil)");
        Ingredient ingredient43 = new Ingredient("1 tsp chili powder");
        Ingredient ingredient44 = new Ingredient("1 tsp cumin");
        Ingredient ingredient45 = new Ingredient("1/2 tsp paprika");
        Ingredient ingredient46 = new Ingredient("2 cloves garlic, minced");
        Ingredient ingredient47 = new Ingredient("1 small onion, thinly sliced");
        Ingredient ingredient48 = new Ingredient("1 red pepper, thinly sliced (or half red, half orange/yellow etc)");
        Ingredient ingredient49 = new Ingredient("1 pound raw shrimp (deveined, peeled or tail on)\n");
        Ingredient ingredient410 = new Ingredient("rice, black beans, guac, salsa etc for serving (optional)\n");
        Step step41 = new Step("In a small bowl, combine lime juice, oil, spiced and garlic and stir to combine.");
        Step step42 = new Step("Place shrimp, onion and peppers in a large bowl. Add spice mixture and stir until everything in well coated.");
        Step step43 = new Step("Pour onto a foil-lined baking sheet.");
        Step step44 = new Step("Bake at 400 degrees F for 8-10 minutes.");
        Step step45 = new Step("Serve over rice and beans, topped with guac and salsa (or in tortillas, or over lettuce, etc).");
        recipe4.addIngredient(ingredient41);ingredient41.setRecipe(recipe4);
        recipe4.addIngredient(ingredient42);ingredient42.setRecipe(recipe4);
        recipe4.addIngredient(ingredient43);ingredient43.setRecipe(recipe4);
        recipe4.addIngredient(ingredient44);ingredient44.setRecipe(recipe4);
        recipe4.addIngredient(ingredient45);ingredient45.setRecipe(recipe4);
        recipe4.addIngredient(ingredient46);ingredient46.setRecipe(recipe4);
        recipe4.addIngredient(ingredient47);ingredient47.setRecipe(recipe4);
        recipe4.addIngredient(ingredient48);ingredient48.setRecipe(recipe4);
        recipe4.addIngredient(ingredient49);ingredient49.setRecipe(recipe4);
        recipe4.addIngredient(ingredient410);ingredient410.setRecipe(recipe4);
        recipe4.addStep(step41);step41.setRecipe(recipe4);
        recipe4.addStep(step42);step42.setRecipe(recipe4);
        recipe4.addStep(step43);step43.setRecipe(recipe4);
        recipe4.addStep(step44);step44.setRecipe(recipe4);
        recipe4.addStep(step45);step45.setRecipe(recipe4);

        recipeDao.save(recipe4);




        Recipe recipe5 = new Recipe("Dressed-Up French Dip Sandwiches",
                "This makeover French dip recipe adds taste and zing to the traditional sandwich. Slow-cooked sweet onions and beef still steal the show, but our mustard spread and roasted pepper relish add taste and color that will make these sandwiches a hit with any crowd.",
                Category.LUNCH, null, 30, 4, 30,
                "http://images.media-allrecipes.com/userphotos/960x960/6256481.jpg");
        user2.addOwnedRecipe(recipe5);
        recipe5.setUser(user2);
        Ingredient ingredient51 = new Ingredient("2 medium sweet onions (such as Vidalia, Maui, or Walla Walla), cut into ½-inch-thick slices and separated into rings (2 cups)");
        Ingredient ingredient52 = new Ingredient("1 (2½ to 3 pound) fresh beef brisket or boneless beef bottom round roast");
        Ingredient ingredient53 = new Ingredient("3 cloves garlic, minced, divided");
        Ingredient ingredient54 = new Ingredient("1 teaspoon dried marjoram, rosemary, or thyme, crushed");
        Ingredient ingredient55 = new Ingredient("¼ teaspoon ground pepper plus ⅛ teaspoon, divided");
        Ingredient ingredient56 = new Ingredient("1 (14.5 ounce) can lower-sodium beef broth");
        Ingredient ingredient57 = new Ingredient("¼ cup water");
        Ingredient ingredient58 = new Ingredient("2 tablespoons Worcestershire sauce");
        Ingredient ingredient59 = new Ingredient("1 large fresh poblano pepper");
        Ingredient ingredient510 = new Ingredient("1 medium poblano peppers");
        Ingredient ingredient511 = new Ingredient("2 tablespoons thinly sliced scallion");
        Ingredient ingredient512 = new Ingredient("2 tablespoons chopped pepperoncini salad peppers");
        Ingredient ingredient513 = new Ingredient("1 teaspoon olive oil");
        Ingredient ingredient514 = new Ingredient("¼ cup light sour cream");
        Ingredient ingredient515 = new Ingredient("¼ cup Dijon mustard");
        Ingredient ingredient516 = new Ingredient("1 tablespoon chopped fresh chives");
        Ingredient ingredient517 = new Ingredient("4 large whole-grain hoagie rolls (2 ounces each)");
        Step step51 = new Step("Place onions in a 4- to 5-quart slow cooker (see Tip). Trim fat from beef. If necessary, cut roast in half to fit into cooker. Place the roast on the onions. Sprinkle with 2 cloves of the minced garlic, the marjoram, and ¼ teaspoon of the ground pepper. Pour broth, the water, and Worcestershire sauce over all.");
        Step step52 = new Step("Cover and cook brisket on Low for 9 to 10 hours or on High for 4½ to 5 hours. (If using bottom round, cover and cook on Low for 8 to 9 hours or on High for 4 to 4½ hours.)");
        Step step53 = new Step("Meanwhile, prepare relish and mustard spread. For relish, preheat oven to 425°F. Line a 15x10-inch baking pan with foil; set aside. Quarter, stem, and seed bell pepper and poblano pepper (see Tip). Place the pepper quarters, cut sides down, in the prepared pan. Roast, uncovered, for 20 to 25 minutes or until the pepper skins are charred. Wrap the foil around the pepper quarters to fully enclose and let stand until cool enough to handle. Using a sharp knife, peel skin off the pepper pieces and discard. Chop the pepper pieces and combine in a small bowl. Stir in scallion, pepperoncini salad peppers, olive oil, and the remaining teaspoon ground pepper. Cover and refrigerate until ready to use.");
        Step step54 = new Step("For the mustard spread, combine sour cream, mustard, chives, and the remaining clove of minced garlic in a small bowl. Cover and refrigerate until ready to use.");
        Step step55 = new Step("Transfer the meat to a cutting board; thinly slice across the grain, removing any visible fat as you slice. Using a slotted spoon, remove the onions from the cooker. Cut rolls crosswise in half. Split the halves horizontally. If desired, toast the rolls. Divide the sliced beef and onion slices among the roll bottoms. Spoon relish over the beef and onions. Spread mustard spread on the cut side of the roll tops before placing on top of the beef and onions; add the roll tops. Skim fat from cooking juices in cooker; pass the juices for dipping the sandwiches.");
        recipe5.addIngredient(ingredient51);ingredient51.setRecipe(recipe5);
        recipe5.addIngredient(ingredient52);ingredient52.setRecipe(recipe5);
        recipe5.addIngredient(ingredient53);ingredient53.setRecipe(recipe5);
        recipe5.addIngredient(ingredient54);ingredient54.setRecipe(recipe5);
        recipe5.addIngredient(ingredient55);ingredient55.setRecipe(recipe5);
        recipe5.addIngredient(ingredient56);ingredient56.setRecipe(recipe5);
        recipe5.addIngredient(ingredient57);ingredient57.setRecipe(recipe5);
        recipe5.addIngredient(ingredient58);ingredient58.setRecipe(recipe5);
        recipe5.addIngredient(ingredient59);ingredient59.setRecipe(recipe5);
        recipe5.addIngredient(ingredient510);ingredient510.setRecipe(recipe5);
        recipe5.addIngredient(ingredient511);ingredient511.setRecipe(recipe5);
        recipe5.addIngredient(ingredient512);ingredient512.setRecipe(recipe5);
        recipe5.addIngredient(ingredient513);ingredient513.setRecipe(recipe5);
        recipe5.addIngredient(ingredient514);ingredient514.setRecipe(recipe5);
        recipe5.addIngredient(ingredient515);ingredient515.setRecipe(recipe5);
        recipe5.addIngredient(ingredient516);ingredient516.setRecipe(recipe5);
        recipe5.addIngredient(ingredient517);ingredient517.setRecipe(recipe5);
        recipe5.addStep(step51);step51.setRecipe(recipe5);
        recipe5.addStep(step52);step52.setRecipe(recipe5);
        recipe5.addStep(step53);step53.setRecipe(recipe5);
        recipe5.addStep(step54);step54.setRecipe(recipe5);
        recipe5.addStep(step55);step55.setRecipe(recipe5);

        recipeDao.save(recipe5);




        Recipe recipe6 = new Recipe("Slow-Cooked Pork Tacos with Chipotle Aioli",
                "Follow this pork taco recipe as is to serve four and you'll have enough shredded pork leftover to make it again next week. It's so good, however, that we recommend doubling the rest of the ingredients and inviting over four more friends to enjoy everything right away!",
                Category.DINNER, null, 30, 4, 30,
                "https://img.taste.com.au/4F5Z2H_-/w720-h480-cfill-q80/taste/2016/11/aussie-style-beef-and-salad-tacos-86525-1.jpeg");
        user2.addOwnedRecipe(recipe6);
        recipe6.setUser(user2);
        Ingredient ingredient61 = new Ingredient("1 (2 to 2½ pound) boneless pork sirloin roast");
        Ingredient ingredient62 = new Ingredient("3 tablespoons reduced-sodium taco seasoning mix");
        Ingredient ingredient63 = new Ingredient("1 (14.5 ounce) can no-salt-added diced tomatoes, undrained");
        Ingredient ingredient64 = new Ingredient("1 cup shredded romaine lettuce");
        Ingredient ingredient65 = new Ingredient("1 cup chopped mango");
        Ingredient ingredient66 = new Ingredient("⅔ cup thin bite-size strips, peeled jicama");
        Ingredient ingredient67 = new Ingredient("½ cup light mayonnaise");
        Ingredient ingredient68 = new Ingredient("2 tablespoons lime juice");
        Ingredient ingredient69 = new Ingredient("2 cloves garlic, minced");
        Ingredient ingredient610 = new Ingredient("½ to 1 teaspoon finely chopped canned chipotle pepper in adobo sauce");
        Ingredient ingredient611 = new Ingredient("8 (6 inch) corn tortillas, warmed");
        Ingredient ingredient612 = new Ingredient("¼ cup coarsely chopped fresh cilantro");
        Step step61 = new Step("Trim fat from roast. Sprinkle with taco seasoning mix; rub in with your fingers. Place the roast in a 3½- or 4-quart slow cooker. Add undrained tomatoes; cover and cook on Low for 7 to 8 hours or on High for 3½ to 4 hours.");
        Step step62 = new Step("Remove the roast, reserving cooking liquid. Shred the meat using two forks. Toss the meat with enough cooking liquid to moisten. Set half of the meat aside (about 2½ cups) and place the remainder in an airtight container for later use");
        Step step63 = new Step("Combine lettuce, mango, and jicama in a medium bowl.");
        Step step64 = new Step("For chipotle aioli, combine mayonnaise, lime juice, garlic, and chipotle pepper in a small bowl.");
        Step step65 = new Step("Serve the shredded meat, the lettuce mixture, and the chipotle aioli in tortillas. Sprinkle with cilantro.");
        recipe6.addIngredient(ingredient61);ingredient61.setRecipe(recipe6);
        recipe6.addIngredient(ingredient62);ingredient62.setRecipe(recipe6);
        recipe6.addIngredient(ingredient63);ingredient63.setRecipe(recipe6);
        recipe6.addIngredient(ingredient64);ingredient64.setRecipe(recipe6);
        recipe6.addIngredient(ingredient65);ingredient65.setRecipe(recipe6);
        recipe6.addIngredient(ingredient66);ingredient66.setRecipe(recipe6);
        recipe6.addIngredient(ingredient67);ingredient67.setRecipe(recipe6);
        recipe6.addIngredient(ingredient68);ingredient68.setRecipe(recipe6);
        recipe6.addIngredient(ingredient69);ingredient69.setRecipe(recipe6);
        recipe6.addIngredient(ingredient610);ingredient610.setRecipe(recipe6);
        recipe6.addIngredient(ingredient611);ingredient611.setRecipe(recipe6);
        recipe6.addIngredient(ingredient612);ingredient612.setRecipe(recipe6);
        recipe6.addStep(step61);step61.setRecipe(recipe6);
        recipe6.addStep(step62);step62.setRecipe(recipe6);
        recipe6.addStep(step63);step63.setRecipe(recipe6);
        recipe6.addStep(step64);step64.setRecipe(recipe6);
        recipe6.addStep(step65);step65.setRecipe(recipe6);

        recipeDao.save(recipe6);

    }
}
