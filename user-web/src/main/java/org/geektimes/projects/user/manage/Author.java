package org.geektimes.projects.user.manage;

public class Author implements AuthorMXBean {

    private volatile static Author sTheOne;
    private volatile String name;
    private volatile String email;
    private volatile String github;
    private volatile Hobbies hobbies;

    public static Author getInstance() {
        if (sTheOne == null) {
            synchronized (Author.class) {
                if (sTheOne == null) {
                    sTheOne = new Author();
                    sTheOne.setEmail("Valora.Sun@gmail.com");
                    sTheOne.setGithub("https://github.com/Valora");
                    sTheOne.setName("Valora");
                    Hobbies hobbies = new Hobbies();
                    hobbies.setMovie("ShawShank");
                    hobbies.setMusic("VVVVVVVV");
                    sTheOne.setHobbies(hobbies);
                }
            }
        }
        return sTheOne;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getGithub() {
        return github;
    }

    @Override
    public void setGithub(String github) {
        this.github = github;
    }

    @Override
    public Hobbies getHobbies() {
        return hobbies;
    }

    @Override
    public void setHobbies(Hobbies hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "Author{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", github='" + github + '\'' +
            ", hobbies=" + hobbies +
            '}';
    }

    public static class Hobbies {

        private volatile String music;
        private volatile String movie;

        public void setMusic(String music) {
            this.music = music;
        }

        public void setMovie(String movie) {
            this.movie = movie;
        }

        public Hobbies() {
        }

        public String getMusic() {
            return music;
        }

        public String getMovie() {
            return movie;
        }

        @Override
        public String toString() {
            return "Hobbies{" +
                "music='" + music + '\'' +
                ", movie='" + movie + '\'' +
                '}';
        }
    }
}
