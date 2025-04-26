SUMMARY = "My custom Linux image."

IMAGE_INSTALL = "packagegroup-core-boot ${CORE_IMAGE_EXTRA_INSTALL}"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image
inherit extrausers

# Set rootfs to 200 MiB by defualt
IMAGE_OVERHEAD_FACTOR ?= "1.0"
IMAGE_ROOTFS_SIZE ?= "204800"
#IMAGE_ROOTFS_EXTRA_SPACE:append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "", d)}"

# Change root password (note the capital -P)
#EXTRA_USERS_PARAMS = "\
#    usermod -P 'toor' root \
#    "

ROOTFS_POSTPROCESS_COMMAND += "set_root_password;"

set_root_password() {
    # Replace 'hashedpassword' with the actual hashed password -> [GM] password is 'toor' -> This comment should be removed.
    sed -i 's|^root:[^:]*:|root:$6$ianK8X1nc7tPdWaU$5U/AUF2nQplWlur/d5cE08VGmyt4NJKKa5tceyeZZ392hvREOV82RozCeQDZZWWJIVn1hmz/XlasuZ69WJ1MA1:|' ${IMAGE_ROOTFS}/etc/shadow
}


# Include customs applications
#IMAGE_INSTALL += "gettemp"
