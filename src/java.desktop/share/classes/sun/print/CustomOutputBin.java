/*
 * Copyright (c) 2024, Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2024, BELLSOFT. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package sun.print;

import java.io.Serial;
import java.util.Map;
import java.util.LinkedHashMap;

import javax.print.attribute.EnumSyntax;
import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.OutputBin;

public final class CustomOutputBin extends OutputBin {

    private String name;

    private static final long serialVersionUID = 3018751086294120717L;

    private static final Map<String, CustomOutputBin> customMap = new LinkedHashMap<>();

    private CustomOutputBin(String name) {
        super(customMap.size());
        this.name = name;
    }

    /**
     * Creates a custom output bin
     */
    public static synchronized CustomOutputBin createOutputBin(String name) {
        return customMap.computeIfAbsent(name, n -> new CustomOutputBin(n));
    }

    /**
     * Returns the string table for super class MediaTray.
     */
    public OutputBin[] getSuperEnumTable() {
      return (OutputBin[])super.getEnumValueTable();
    }

    /**
     * Returns the string table for class CustomOutputBin.
     */
    @Override
    protected String[] getStringTable() {
      return customMap.keySet().toArray(new String[customMap.size()]);
    }

    /**
     * Returns a custom bin name
     */
    public String getCustomName() {
        return name;
    }

    /**
     * Returns the enumeration value table for class CustomOutputBin.
     */
    @Override
    protected CustomOutputBin[] getEnumValueTable() {
      return customMap.values().toArray(new CustomOutputBin[customMap.size()]);
    }

    @Override
    public String toString() {
        return name;
    }
}
