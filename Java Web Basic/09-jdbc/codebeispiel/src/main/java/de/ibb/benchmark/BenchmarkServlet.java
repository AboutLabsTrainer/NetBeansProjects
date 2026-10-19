package de.ibb.benchmark;

import de.ibb.dao.ProductDAO;
import de.ibb.dao.ProductDAOImpl;
import de.ibb.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

/**
 * Startet die Messungen und gibt das Ergebnis an die JSP weiter.
 */
@WebServlet(name = "BenchmarkServlet", urlPatterns = {"/benchmark"})
public class BenchmarkServlet extends HttpServlet {

    private final ProductDAO dao = new ProductDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/benchmark.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = Optional.ofNullable(req.getParameter("op")).orElse("selectById");
        int iterations = parseInt(req.getParameter("iterations"), 1000);
        boolean usePrepared = "on".equalsIgnoreCase(req.getParameter("usePrepared"));
        boolean useBatch = "on".equalsIgnoreCase(req.getParameter("useBatch"));

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("op", op);
        result.put("iterations", iterations);
        result.put("prepared", usePrepared);
        result.put("batch", useBatch);

        long start = System.nanoTime();
        try {
            switch (op) {
                case "selectById" -> runSelectById(iterations);
                case "singleInsert" -> runSingleInsert(iterations);
                case "batchInsert" -> runBatchInsert(iterations);
                case "txCommitRollback" -> dao.transactionCommitAndRollback();
                default -> runSelectById(iterations);
            }
        } catch (Exception e) {
            throw new ServletException("Benchmark failed: " + e.getMessage(), e);
        }
        long durationNs = System.nanoTime() - start;
        long durationMs = Math.max(1, durationNs / 1_000_000);
        double throughput = Math.round((iterations * 1000.0 / durationMs) * 100.0) / 100.0;

        result.put("durationMs", durationMs);
        result.put("throughput", throughput);

        req.setAttribute("result", result);
        req.getRequestDispatcher("/WEB-INF/jsp/benchmark.jsp").forward(req, resp);
    }

    private void runSelectById(int iterations) throws Exception {
        int id = 1;
        for (int i = 0; i < iterations; i++) {
            dao.findById(id);
            id = (id % 1000) + 1; // cycle 1..1000
        }
    }

    private void runSingleInsert(int iterations) throws Exception {
        for (int i = 0; i < iterations; i++) {
            dao.insertSingle(new Product("Single-" + i, 9.99, 10));
        }
    }

    private void runBatchInsert(int iterations) throws Exception {
        List<Product> list = new ArrayList<>(Math.min(iterations, 10_000));
        for (int i = 0; i < iterations; i++) {
            list.add(new Product("Batch-" + i, 5.55, 5));
        }
        dao.insertBatch(list);
    }

    private int parseInt(String s, int def) {
        try { return Integer.parseInt(s); } catch (Exception e) { return def; }
    }
}
