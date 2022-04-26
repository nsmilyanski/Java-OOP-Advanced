package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeroRepositoryTests {
    private Hero hero1;
    private Hero hero2;
    private Hero hero3;
    private HeroRepository heroRepository;


    @Before
    public void setup() {
        hero1 = new Hero("Pesho", 25);
        hero2 = new Hero("Gosho", 30);
        hero3 = new Hero("Sasho", 70);

        heroRepository = new HeroRepository();
    }

    @Test
    public void testCountMethod() {
        heroRepository.create(hero1);
        heroRepository.create(hero2);
        heroRepository.create(hero3);

        int count = heroRepository.getCount();

        Assert.assertEquals(3, count);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateMethodIfThrowException() {
        heroRepository.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateMethodToAddHeroesWithSameName() {
        heroRepository.create(hero1);
        heroRepository.create(hero1);
    }

    @Test
    public void testCreateMethod() {
        heroRepository.create(hero1);
        Hero pesho = heroRepository.getHero("Pesho");

        Assert.assertEquals("Pesho", pesho.getName());
    }

    @Test
    public void testGetHeroWithHighestLevelReturnNull() {
        heroRepository.create(hero1);
        heroRepository.create(hero2);

        Hero mitko = heroRepository.getHero("Mitko");
        Assert.assertEquals(null, mitko);
    }

    @Test
    public void getHeroWithHighestLevelMethod() {
        heroRepository.create(hero1);
        heroRepository.create(hero2);
        heroRepository.create(hero3);

        Hero heroWithHighestLevel = heroRepository.getHeroWithHighestLevel();

        Assert.assertEquals(70, heroWithHighestLevel.getLevel());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getHeroesMethod() {
        heroRepository.create(hero1);
        heroRepository.create(hero2);
        heroRepository.create(hero3);

        heroRepository.getHeroes().add(new Hero("Ivo", 90));

        int count = heroRepository.getCount();

        Assert.assertEquals(3, count);
    }

    @Test(expected = NullPointerException.class)
    public void removeIfThrowException() {
        heroRepository.create(hero1);
        heroRepository.create(hero2);

        heroRepository.remove(null);
    }

    @Test
    public void removeMethod() {
        heroRepository.create(hero1);
        heroRepository.create(hero2);

        heroRepository.remove("Pesho");

        int count = heroRepository.getCount();

        Assert.assertEquals(1, count);
    }

}
