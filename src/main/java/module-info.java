module ru.vsu.cs.vinyukov.task2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    exports ru.vsu.cs.vinyukov.task2.model;
    opens ru.vsu.cs.vinyukov.task2.model to javafx.fxml;
    exports ru.vsu.cs.vinyukov.task2.service;
    opens ru.vsu.cs.vinyukov.task2.service to javafx.fxml;
    exports ru.vsu.cs.vinyukov.task2.ui;
    opens ru.vsu.cs.vinyukov.task2.ui to javafx.fxml;
}