import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

public class GroupTest {

    @Test
    public void test_ungroup() throws Exception {

        Collection<Transformable> groupMembers = new HashSet<>();
        groupMembers.add(new RoundedBoxElement(new Transform(Optional.empty()))); //default location
        Transformable groupMember = new HeadlineElement(new Transform(Optional.empty()));
        groupMember.getTransform().move(2, 3);
        Transformable groupMember2 = new ParagraphElement(new Transform(Optional.of(groupMember.getTransform())));
        groupMember2.getTransform().move(1, 1);
        groupMembers.add(groupMember);
        Group testGroup = new Group(new Transform(Optional.empty()), groupMembers);
        Set<Pair<Integer>> oldGroupMemberLocations = getGroupMemberLocations(groupMembers);
        groupMembers = testGroup.ungroup();
        Set<Pair<Integer>> newGroupMemberLocations = getGroupMemberLocations(groupMembers);
        Assert.assertEquals(oldGroupMemberLocations, newGroupMemberLocations);
    }

    private Set<Pair<Integer>> getGroupMemberLocations(Collection<Transformable> groupMembers) {
        Set<Pair<Integer>> groupMemberLocations = new HashSet<Pair<Integer>>();
        for(Transformable groupMember : groupMembers) {
            groupMemberLocations.add(groupMember.getTransform().getGlobalLocation());
        }
        return groupMemberLocations;
    }

}