import jnr.ffi.byref.IntByReference;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class JnrHdf5Test {
    @BeforeMethod
    public void setUp() throws Exception {

    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testLoad() throws Exception {
        JnrHdf5.H5Lib lib = JnrHdf5.load();

        final IntByReference maj = new IntByReference();
        final IntByReference min = new IntByReference();
        final IntByReference rel = new IntByReference();

        final long t0 = System.currentTimeMillis();
        final int ret = lib.H5get_libversion(maj, min, rel);
        final long tend = System.currentTimeMillis();

        assertTrue(ret >= 0, "failed to get library version");
        System.out.printf("Loaded libhdf5 v%d.%d.%d in %d ms\n", maj.intValue(), min.intValue(), rel.intValue(), tend - t0);

        lib.H5close();
    }

}