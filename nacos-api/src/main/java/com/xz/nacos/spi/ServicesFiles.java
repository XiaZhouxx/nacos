package com.xz.nacos.spi;

import com.google.common.base.Charsets;
import com.google.common.io.Closer;

import java.io.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author xz
 * @date 2023/6/23 10:56
 */
public class ServicesFiles {
    public static final String SERVICES_PATH = "META-INF/services";

    private ServicesFiles() {
    }

    static String getPath(String serviceName) {
        return "META-INF/services/" + serviceName;
    }

    static Set<String> readServiceFile(InputStream input) throws IOException {
        HashSet<String> serviceClasses = new HashSet();
        Closer closer = Closer.create();

        try {
            BufferedReader r = (BufferedReader)closer.register(new BufferedReader(new InputStreamReader(input, Charsets.UTF_8)));

            String line;
            while((line = r.readLine()) != null) {
                int commentStart = line.indexOf(35);
                if (commentStart >= 0) {
                    line = line.substring(0, commentStart);
                }

                line = line.trim();
                if (!line.isEmpty()) {
                    serviceClasses.add(line);
                }
            }

            HashSet var11 = serviceClasses;
            return var11;
        } catch (Throwable var9) {
            throw closer.rethrow(var9);
        } finally {
            closer.close();
        }
    }

    static void writeServiceFile(Collection<String> services, OutputStream output) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, Charsets.UTF_8));
        Iterator i$ = services.iterator();

        while(i$.hasNext()) {
            String service = (String)i$.next();
            writer.write(service);
            writer.newLine();
        }

        writer.flush();
    }
}
