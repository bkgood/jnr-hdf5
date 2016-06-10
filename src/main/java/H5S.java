import jnr.ffi.Pointer;
import jnr.ffi.annotations.In;
import jnr.ffi.annotations.Out;

interface H5S {
    int H5Sget_simple_extent_ndims(@In int space_id);

    int H5Sget_simple_extent_dims(@In int space_id, @Out Pointer dims, @Out Pointer maxdims);
}
