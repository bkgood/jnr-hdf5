import jnr.ffi.Pointer;
import jnr.ffi.annotations.In;
import jnr.ffi.annotations.Out;

interface H5D {
    int H5Dopen(@In int loc_id, @In String name, @In int dapl_id);

    int H5Dget_space(@In int dataset_id);

    int H5Dread(@In int dataset_id, @In int mem_type_id, @In int mem_space_id, @In int fild_space_id, @In int xfer_plist_id, @Out Pointer buf);
}
