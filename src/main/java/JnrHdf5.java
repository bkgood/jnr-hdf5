import jnr.ffi.LibraryOption;
import jnr.ffi.LibraryLoader;

public class JnrHdf5 {
    private static final String libraryName = "hdf5";
    private static final LibraryLoader<H5Lib> libraryLoader = LibraryLoader.create(H5Lib.class)
            .library(libraryName)
            .failImmediately()
            .option(LibraryOption.LoadNow, true)
            .option(LibraryOption.IgnoreError, true);

    public static H5Lib load() {
        final H5Lib lib = libraryLoader.load();

        lib.H5dont_atexit();
        lib.H5open();

        return lib;
    }

    public interface H5Lib extends H5, H5F, H5D, H5S {
    }
}
