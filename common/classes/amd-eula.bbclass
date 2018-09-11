python do_eula_check () {
    eula = d.getVar('ACCEPT_AMD_EULA', True)
    eula_file = d.getVar('AMD_EULA_FILE', True)
    bbfile = d.getVar('FILE', True)
    pkg = d.getVar('PN', True)
    if eula == None:
        bb.fatal("The package '%s' contains code that must be used solely in conjunction "
                 "with AMD products. By using the software, the end-user agrees that the "
                 "software will be used solely in conjunction with AMD products. "
                 "If you agree to the terms please write: "
                 "ACCEPT_AMD_EULA = \"1\" in '%s'. "
                 "or otherwise remove '%s' from your build. For full license terms please "
                 "have a look at '%s'." % (pkg, bbfile, pkg, eula_file))
    elif eula == '0':
        bb.fatal("To use '%s' you need to accept the AMD EULA." % pkg)
    else:
        bb.note("AMD EULA has been accepted for '%s'" % pkg)
}
addtask eula_check after do_fetch before do_unpack
