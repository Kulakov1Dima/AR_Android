package com.ar;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;

        import com.google.ar.core.Config;
        import com.google.ar.core.Session;
        import com.google.ar.core.exceptions.UnavailableApkTooOldException;
        import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
        import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
        import com.google.ar.core.exceptions.UnavailableSdkTooOldException;
        import com.google.ar.sceneform.ux.ArFragment;

public class MainActivity extends AppCompatActivity {

    private ArFragment arFragment;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ar_fragment);

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ar_fragment);

        try {
            createSession();
        } catch (UnavailableDeviceNotCompatibleException | UnavailableSdkTooOldException |
                 UnavailableArcoreNotInstalledException | UnavailableApkTooOldException e) {
            session.close();
            throw new RuntimeException(e);
        }

        // Создаем конфигурацию для включения обнаружения вертикальных плоскостей
        Config config = new Config(session);
        config.setUpdateMode(Config.UpdateMode.LATEST_CAMERA_IMAGE);
        config.setFocusMode(Config.FocusMode.AUTO);
        config.setUpdateMode(Config.UpdateMode.LATEST_CAMERA_IMAGE);
        config.setPlaneFindingMode(Config.PlaneFindingMode.VERTICAL);

        // Устанавливаем созданную конфигурацию
        session.configure(config);
    }

    public void createSession() throws UnavailableDeviceNotCompatibleException, UnavailableSdkTooOldException, UnavailableArcoreNotInstalledException, UnavailableApkTooOldException {
        // Create a new ARCore session.
        session = new Session(arFragment.getContext());

        // Create a session config.
        Config config = new Config(session);

        // Do feature-specific operations here, such as enabling depth or turning on
        // support for Augmented Faces.

        // Configure the session.
        session.configure(config);
    }
}