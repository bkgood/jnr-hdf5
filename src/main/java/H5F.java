import jnr.ffi.annotations.In;

interface H5F {
    int H5Fopen(@In String fileName, @In int flags, @In int fapl_id);
}
