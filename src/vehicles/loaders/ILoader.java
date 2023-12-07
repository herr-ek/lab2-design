package vehicles.loaders;

public interface ILoader {

    void load(Loadable vehicle) throws LoaderException;
    Loadable unload(Loadable vehicle) throws LoaderException;
    Loadable unload() throws LoaderException;
}
