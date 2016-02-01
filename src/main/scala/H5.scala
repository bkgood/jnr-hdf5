import jnr.ffi.byref.IntByReference
import jnr.ffi.annotations.Out

object H5 {
  import jnr.ffi.LibraryLoader
  val libName = "hdf5"
  def apply() = {
    val lib = LibraryLoader.create(classOf[H5]).load(libName)
    lib.H5dont_atexit()
    lib.H5open()

    lib
  }
}

trait H5 {
  //val H5F_ACC_TRUNC
  //val H5P_DEFAULT
  //val H5T_NATIVE_DOUBLE
  //val H5S_ALL

  type Err = Int

  def H5get_libversion(@Out majnum: IntByReference, @Out minnum: IntByReference, @Out relnum: IntByReference): Err
  def H5dont_atexit(): Err
  def H5open(): Err
  def H5close(): Err

  //def H5Fcreate
  //def H5Sset_extent_simple
  //def H5Dcreate
  //def H5Dwrite
  //def H5Dclose
  //def H5Sclose
  //def H5Fclose
}

object Main {
  def main(args: Array[String]): Unit = {
    val lib = H5()
    lib.H5dont_atexit()
    lib.H5open()
    val (maj, min, rel) = (new IntByReference, new IntByReference, new IntByReference)
    val ret = lib.H5get_libversion(maj, min, rel)
    if (ret < 0)
      println("it failed")
    else
      println(s"Loaded libhdf5 ${maj.intValue}.${min.intValue}.${rel.intValue}")
    lib.H5close()
    ()
  }
}
