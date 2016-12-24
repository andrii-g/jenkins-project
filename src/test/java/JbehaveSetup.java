import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;
import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.RozetkaTestSteps;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.jbehave.core.reporters.Format.*;

/**
 * Created by User on 11.12.2016.
 */
@RunWith(JUnitReportingRunner.class)

public class JbehaveSetup extends JUnitStories{
    @Override
    public Configuration configuration() {
        Class<? extends Embeddable> embeddableClass = this.getClass();
        ParameterConverters parameterConverters = new ParameterConverters();
        ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(
                new LocalizedKeywords(), new LoadFromClasspath(embeddableClass), parameterConverters);
        parameterConverters.addConverters(new ParameterConverters.DateConverter(new SimpleDateFormat("yyyy-MM-dd")),
                new ParameterConverters.ExamplesTableConverter(examplesTableFactory));
        return new MostUsefulConfiguration().useStoryLoader(new LoadFromClasspath(embeddableClass))
                .useStoryParser(new RegexStoryParser(examplesTableFactory))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass))
                        .withDefaultFormats().withFormats(CONSOLE, TXT, HTML, XML))
                .useParameterConverters(parameterConverters);
    }

    @Override
    public List<CandidateSteps> candidateSteps() {
        try {
            return new InstanceStepsFactory(configuration(),
                    new RozetkaTestSteps()).createCandidateSteps();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected List<String> storyPaths() {
        return Arrays.asList("rozetka.story");
    }

    @Test
    public void run() throws Throwable {
        super.run();
    }
}
