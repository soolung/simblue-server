package com.soogung.simblue.domain.group.exception;


import com.soogung.simblue.domain.group.exception.error.GroupErrorProperty;
import com.soogung.simblue.domain.user.exception.UserNotFoundException;
import com.soogung.simblue.global.error.exception.SimblueException;

public class GroupNotFoundException extends SimblueException {

    public final static GroupNotFoundException EXCEPTION = new GroupNotFoundException();
    private GroupNotFoundException() { super(GroupErrorProperty.GROUP_NOT_FOUND); }
}
