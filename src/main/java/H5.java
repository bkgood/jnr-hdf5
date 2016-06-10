import jnr.ffi.annotations.Out;
import jnr.ffi.byref.IntByReference;

interface H5 {
    int H5get_libversion(@Out IntByReference majnum, @Out IntByReference minnum, @Out IntByReference relnum);

    int H5open();

    int H5close();

    int H5dont_atexit();
}
