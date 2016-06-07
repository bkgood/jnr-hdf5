import jnr.ffi.LibraryOption;
import jnr.ffi.Pointer;
import jnr.ffi.annotations.In;
import jnr.ffi.byref.IntByReference;
import jnr.ffi.annotations.Out;
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

    private interface H5 {
        int H5get_libversion(@Out IntByReference majnum, @Out IntByReference minnum, @Out IntByReference relnum);
        int H5open();
        int H5close();
        int H5dont_atexit();
    }

    private interface H5F {
        int H5Fopen(@In String fileName, @In int flags, @In int fapl_id);
    }

    private interface H5D {
        int H5Dopen(@In int loc_id, @In String name, @In int dapl_id);
        int H5Dget_space(@In int dataset_id);
        int H5Dread(@In int dataset_id, @In int mem_type_id, @In int mem_space_id, @In int fild_space_id, @In int xfer_plist_id, @Out Pointer buf);
    }

    private interface H5S {
        int H5Sget_simple_extent_ndims(@In int space_id);
        int H5Sget_simple_extent_dims(@In int space_id, @Out Pointer dims, @Out Pointer maxdims);
    }

    public interface H5Lib extends H5, H5F, H5D, H5S {}

    public static void main(String[] args) {
        final H5 lib = JnrHdf5.load();

        final IntByReference maj = new IntByReference();
        final IntByReference min = new IntByReference();
        final IntByReference rel = new IntByReference();

        for (int i = 0; i < 1; ++i) {
            final long t0 = System.currentTimeMillis();
            final int ret = lib.H5get_libversion(maj, min, rel);
            final long tend = System.currentTimeMillis();

            if (ret < 0) {
                System.out.println("it failed");
            } else {
                System.out.printf("Loaded libhdf5 v%d.%d.%d in %d ms\n", maj.intValue(), min.intValue(), rel.intValue(), tend - t0);
            }
        }

        lib.H5close();
    }
}
