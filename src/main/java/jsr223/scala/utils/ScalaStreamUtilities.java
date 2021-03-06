/*
 * ProActive Parallel Suite(TM):
 * The Open Source library for parallel and distributed
 * Workflows & Scheduling, Orchestration, Cloud Automation
 * and Big Data Analysis on Enterprise Grids & Clouds.
 *
 * Copyright (c) 2007 - 2017 ActiveEon
 * Contact: contact@activeeon.com
 *
 * This library is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation: version 3 of
 * the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * If needed, contact us to obtain a release under GPL Version 2 or 3
 * or a different license than the AGPL.
 */
package jsr223.scala.utils;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import javax.script.ScriptContext;


/**
 * @author ActiveEon Team
 * @since 05/10/2017
 */
public class ScalaStreamUtilities {

    public static void pipe(Reader from, Writer to) throws IOException {
        char[] buff = new char[1024];
        int n = from.read(buff);
        while (n != -1) {
            to.write(buff, 0, n);
            to.flush();
            n = from.read(buff);
        }
        from.close();
    }

    public static void attachStreams(ScriptContext context, Writer processOutput, Writer processError,
            Reader processInput) {
        if (processOutput != null) {
            // Attach to std output
            context.setWriter(processOutput);
        }

        if (processError != null) {
            // Attach error output
            context.setErrorWriter(processError);
        }

        if (processInput != null) {
            // Attach process input
            context.setReader(processInput);
        }
    }
}
