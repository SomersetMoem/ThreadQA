package utils;

import com.github.javafaker.Faker;
import model.swagger.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class RandomTestData {
    private static Random random = new Random();
    private static Faker faker = new Faker();

    public static GamesItem getRandomGames() {
        SimilarDlc similarDlc = SimilarDlc.builder()
                .isFree(false)
                .dlcNameFromAnotherGame(faker.funnyName().name())
                .build();

        DlcsItem dlcsItem = DlcsItem.builder()
                .rating(faker.random().nextInt(10))
                .price(faker.random().nextInt(1, 500))
                .description(faker.funnyName().name())
                .dlcName(faker.dragonBall().character())
                .isDlcFree(false)
                .similarDlc(similarDlc)
                .build();

        Requirements requirements = Requirements.builder()
                .ramGb(faker.random().nextInt(4, 16))
                .osName("windows")
                .hardDrive(faker.random().nextInt(30, 70))
                .videoCard("NVIDEA")
                .build();

        return GamesItem.builder()
                .requirements(requirements)
                .gameId(10)
                .genre(faker.book().genre())
                .price(faker.random().nextInt(400))
                .description("CS GO")
                .company(faker.company().name())
                .isFree(false)
                .title(faker.beer().name())
                .rating(faker.random().nextInt(10))
                .publishDate(LocalDateTime.now().toString())
                .requiredAge(faker.random().nextBoolean())
                .tags(Arrays.asList("shooter", "quests"))
                .dlcs(Collections.singletonList(dlcsItem))
                .build();
    }

    public static FullUser getRandomUserWithGames() {
        int randomNumber = Math.abs(random.nextInt());

        GamesItem gamesItem = getRandomGames();

        return FullUser.builder()
                .login(faker.name().username() + randomNumber)
                .pass(faker.internet().password())
                .games(Collections.singletonList(getRandomGames()))
                .build();
    }

    public static FullUser getRandomFullUser() {
        int randomNumber = Math.abs(random.nextInt());
        return FullUser.builder()
                .login("threadQA" + randomNumber)
                .pass("asd11")
                .build();
    }

    public static FullUser getAdminUser() {
        return FullUser.builder()
                .login("admin")
                .pass("admin")
                .build();
    }
}
