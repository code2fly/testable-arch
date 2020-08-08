package io.pillopl.testablearch.ex2.acl;

import io.pillopl.testablearch.ex2.acl.toggles.NewModelToggles;
import io.pillopl.testablearch.ex2.bigballofmud.BigBallOfMud;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.repository.FeatureState;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BigBallOfMud.class, AclConfiguration.class})
public class SanityFeatureToggleTest {

    @Autowired
    FeatureManager manager;

    @Test
    public void makeSureNewModelIsDisabledOnProd() {
        boolean isNewModelActive = manager.isActive(NewModelToggles.RECONCILE_NEW_MODEL);
        assertFalse(isNewModelActive);
    }


    @Test
    public void makeSureWhenToggleChangedNewModelIsUsed() {
        FeatureState reconcileAndUseNewModelFeatureState = manager.getFeatureState(NewModelToggles.RECONCILE_AND_USE_NEW_MODEL)
                .enable();
        manager.setFeatureState(reconcileAndUseNewModelFeatureState);
        assertTrue(manager.isActive(NewModelToggles.RECONCILE_AND_USE_NEW_MODEL));
        assertFalse(manager.isActive(NewModelToggles.RECONCILE_NEW_MODEL));
    }

}